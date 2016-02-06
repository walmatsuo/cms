package br.cms.Controller.Atendimento.Evento;

import br.cms.Model.Evento;
import br.cms.DAO.EventoDAO;
import br.cms.DAO.EventoInscricaoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.EventoInscricao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveEvento implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "AtendimentoController?action=Evento.ListaEvento";

        long idEvento = Long.parseLong(req.getParameter("idEvento"));

        //Excluir evento
        Evento eventoOBJ = new Evento();
        eventoOBJ.setIdEvento(idEvento);
        eventoOBJ.setStatusEvento("x");

        EventoDAO eventoDAO = new EventoDAO();
        eventoDAO.alterarStatus(eventoOBJ);
        
        //Excluir inscrições do evento
        EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
        eventoInscricaoOBJ.setEvento(eventoOBJ);
        eventoInscricaoOBJ.setStatusEventoInscricao("x");
        
        EventoInscricaoDAO eventoInscricaoDAO = new EventoInscricaoDAO();
        eventoInscricaoDAO.alterarStatusPorEvento(eventoInscricaoOBJ);
        
        req.setAttribute("msg", "Evento excluído com sucesso!");

        return pagina;
    }

}
