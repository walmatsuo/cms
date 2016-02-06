package br.cms.Controller.Sociedade.Reserva;

import br.cms.DAO.EspacoDAO;
import br.cms.DAO.ReservaDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaUsuario;
import br.cms.Model.Espaco;
import br.cms.Model.Reserva;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CadastraReserva implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String title = req.getParameter("title");
        String inicioSt = req.getParameter("start");
        String fimSt = req.getParameter("end");

        //Iniciando tratamento de Data
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date inicio = null;
        Date fim = null;

        try {
            inicio = df.parse(inicioSt);
        } catch (ParseException e) {
            throw new RuntimeException("Data inválida: " + inicioSt + ", tente novamente com uma data válida.");
        }

        try {
            fim = df.parse(fimSt);
        } catch (ParseException e) {
            throw new RuntimeException("Data inválida: " + fimSt + ", tente novamente com uma data válida.");
        }
        //Fim do tratamento de data

        // Removendo 00:00:01 do fim da Reserva
        Calendar fimCalendar = Calendar.getInstance();
        fimCalendar.setTime(fim);

        fimCalendar.add(Calendar.SECOND, -1);

        fim = fimCalendar.getTime();

        long idSocio = 0;
        long idEspaco = 0;

        HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
        Usuario usuarioOBJ = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

        if (usuarioOBJ.getCategoriaUsuario().equals(CategoriaUsuario.Sociedade)) {
            idSocio = usuarioOBJ.getLogin();
            idEspaco = Long.parseLong(req.getParameter("espaco"));
        } else {
            idSocio = Long.parseLong(req.getParameter("socio"));
            idEspaco = Long.parseLong(req.getParameter("espaco"));
        }

        Reserva reservaOBJ = new Reserva();
        reservaOBJ.setTitle(title);
        reservaOBJ.setStart(inicio);
        reservaOBJ.setEnd(fim);
        reservaOBJ.setStatusReserva("a");

        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(idSocio);

        Espaco espacoOBJ = new Espaco();
        espacoOBJ.setIdEspaco(idEspaco);

        reservaOBJ.setSocioReserva(socioOBJ);
        reservaOBJ.setEspacoReserva(espacoOBJ);

        ReservaDAO reservaDAO = new ReservaDAO();
        boolean intervaloValidado = reservaDAO.validarIntervalo(reservaOBJ);
        // Validando se intervalo está livre
        if (intervaloValidado) {
            // Cadastrando reserva
            reservaDAO.cadastrar(reservaOBJ);
            
            req.setAttribute("msg", "Reserva liberada e cadastrada!");
        } else {
            
            EspacoDAO espacoDAO = new EspacoDAO();
            espacoOBJ = espacoDAO.consultarPorId(espacoOBJ);

            // Recuperando somente o dia desejado
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
            String dataReserva = df1.format(reservaOBJ.getStart());

            // Preparando variáveis Date com horário abertura e fechamento do Espaço
            Date inicioDate = null;
            Date fimDate = null;

            String horaAbertura = " " + espacoOBJ.getAbertura() + ":00:00";
            String horaFechamento = " " + espacoOBJ.getFechamento() + ":00:00";

            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                inicioDate = df2.parse(dataReserva.concat(horaAbertura));
            } catch (Exception e) {
                System.out.println("Erro Conversão de data: " + dataReserva.concat(horaAbertura) + ", tente novamente com uma data válida.");
            }

            try {
                fimDate = df2.parse(dataReserva.concat(horaFechamento));
            } catch (Exception e) {
                throw new Exception("Data inválida" + dataReserva.concat(horaFechamento) + ", tente novamente com uma data válida.");
            }

            // ArrayList para armazenar os horários livres
            ArrayList<Integer> listaHorasLivres = new ArrayList<Integer>();

            // ArrayList para armazenar os horários ocupados
            ArrayList<Integer> listaHorasOcupadas = new ArrayList<Integer>();

            // Preenchendo ArrayList com os horários possíveis
            for (int i = espacoOBJ.getAbertura(); i < espacoOBJ.getFechamento(); i++) {
                listaHorasLivres.add(i);
            }

            // Preparando reservaOBJ para pesquisar reservas existentes no dia desejado
            reservaOBJ.setEspacoReserva(espacoOBJ);
            reservaOBJ.setStart(inicioDate);
            reservaOBJ.setEnd(fimDate);

            // Recuperando todas reservas existentes no dia desejado
            ArrayList<Reserva> listaReserva = reservaDAO.listarOcupados(reservaOBJ);

            // Recuperando horas ocupadas pelas reservas existentes no dia desejado
            for (Reserva reserva : listaReserva) {
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(reserva.getStart());

                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTime(reserva.getEnd());
                
                for (int i : listaHorasLivres) {
                    if (startCalendar.get(Calendar.HOUR_OF_DAY) == i) {
                        for (int j = i; j <= endCalendar.get(Calendar.HOUR_OF_DAY); j++) {
                            listaHorasOcupadas.add(j);
                        }
                    }
                }
            }

            // Removendo horas ocupadas do ArrayList de horas livres
            listaHorasLivres.removeAll(listaHorasOcupadas);

            DateFormat df3 = new SimpleDateFormat("dd/MM/yyyy");
            String dataOcupada = df3.format(reservaOBJ.getStart());

            // Preparando mensagem de retorno com as horas livres(caso exista)
            String msg = "Espaço: " + espacoOBJ.getNomeEspaco() + " - " + dataOcupada + " - Livre: ";

            if (listaHorasLivres.size() > 0) {
                StringBuilder sb = new StringBuilder();

                for (int i : listaHorasLivres) {
                    if (i < listaHorasLivres.get(listaHorasLivres.size() - 1)) {
                        sb.append(i + ":00 | ");
                    } else {
                        sb.append(i + ":00.");
                    }
                }

                String msgHorariosLivres = sb.toString();

                msg = msg.concat(msgHorariosLivres);
            } else {

                msg = "Espaço: " + espacoOBJ.getNomeEspaco() + " - " + dataOcupada + " - Todos os horários estão ocupados.";
            }

            req.setAttribute("msg", msg);
        }

        return null;
    }

}
