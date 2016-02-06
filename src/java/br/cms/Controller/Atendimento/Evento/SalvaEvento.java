package br.cms.Controller.Atendimento.Evento;

import br.cms.Model.Evento;
import br.cms.DAO.EventoDAO;
import br.cms.Interface.Executa;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalvaEvento implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "AtendimentoController?action=Evento.ListaEvento";

        String idEvento = req.getParameter("idEvento");
        String titulo = req.getParameter("titulo");
        String descricao = req.getParameter("descricao");
        String local = req.getParameter("local");

        // Recuperando e convertendo para Date
        String dataSt = req.getParameter("data");
        Date data = null;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            data = df.parse(dataSt);
        } catch (ParseException e) {
            throw new RuntimeException("Data inválida" + dataSt + "");
        }

        if (idEvento.equals("")) {
            Evento eventoOBJ = new Evento();
            eventoOBJ.setData(data);
            eventoOBJ.setTitulo(titulo);
            eventoOBJ.setDescricao(descricao);
            eventoOBJ.setLocal(local);
            eventoOBJ.setStatusEvento("a");

            EventoDAO eventoDAO = new EventoDAO();
            eventoDAO.cadastrar(eventoOBJ);
            
            req.setAttribute("msg", "Evento " + eventoOBJ.getTitulo()+ " cadastrado com sucesso!");
        } else {
            Evento eventoOBJ = new Evento();
            eventoOBJ.setIdEvento(Long.parseLong(idEvento));
            eventoOBJ.setData(data);
            eventoOBJ.setTitulo(titulo);
            eventoOBJ.setDescricao(descricao);
            eventoOBJ.setLocal(local);

            EventoDAO eventoDAO = new EventoDAO();
            eventoDAO.alterar(eventoOBJ);
            
            req.setAttribute("msg", "Evento " + eventoOBJ.getTitulo()+ " atualizado com sucesso!");
        }

        return pagina;
    }

}
