package br.cms.Controller.Atendimento.Evento;

import br.cms.Model.Evento;
import br.cms.DAO.EventoDAO;
import br.cms.Interface.Executa;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlteraEvento implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "atendimento/eventoForm.jsp";

        long idEvento = Long.parseLong(req.getParameter("idEvento"));

        Evento eventoOBJ = new Evento();
        eventoOBJ.setIdEvento(idEvento);

        EventoDAO eventoDAO = new EventoDAO();
        eventoOBJ = eventoDAO.consultarPorId(eventoOBJ);

        req.setAttribute("evento", eventoOBJ);

        return pagina;
    }

}
