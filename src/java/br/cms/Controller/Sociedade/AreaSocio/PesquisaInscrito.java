package br.cms.Controller.Sociedade.AreaSocio;

import br.cms.DAO.EventoDAO;
import br.cms.DAO.EventoInscricaoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Evento;
import br.cms.Model.EventoInscricao;
import br.cms.Model.Socio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PesquisaInscrito implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/eventoInscritoListar.jsp";

        String nomeEvento = req.getParameter("nomeEventoInscrito");
        long idSocio = Long.parseLong(req.getParameter("idSocio"));

        Evento eventoOBJ = new Evento();
        eventoOBJ.setTitulo(nomeEvento);

        EventoDAO eventoDAO = new EventoDAO();
        ArrayList<Evento> eventoList = eventoDAO.pesquisar(eventoOBJ);

        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(idSocio);

        EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
        eventoInscricaoOBJ.setSocio(socioOBJ);

        EventoInscricaoDAO eventoInscricaoDAO = new EventoInscricaoDAO();
        ArrayList<EventoInscricao> eventoInscricaoList = eventoInscricaoDAO.listarTodosEventos(eventoInscricaoOBJ);
        ArrayList<EventoInscricao> eventoInscricaoPesqList = new ArrayList<EventoInscricao>();
        
        for (Evento evento : eventoList) {
            for (EventoInscricao eventoInscricao : eventoInscricaoList){
                if (evento.getIdEvento() == eventoInscricao.getEvento().getIdEvento()){
                    eventoInscricao.setEvento(evento);
                    eventoInscricaoPesqList.add(eventoInscricao);
                    
                }
            }
        }

        req.setAttribute("listaEvento", eventoInscricaoPesqList);
        req.setAttribute("socioInscrito", socioOBJ);
        req.setAttribute("msg", "Resultado: " + eventoInscricaoPesqList.size() + " evento(s)!");

        return pagina;
    }
}

