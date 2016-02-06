package br.cms.Controller.Atendimento.Evento;

import br.cms.Model.Evento;
import br.cms.Model.EventoInscricao;
import br.cms.DAO.EventoInscricaoDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Socio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaInscrito implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "atendimento/inscritoListar.jsp";

        long idEvento = Long.parseLong(req.getParameter("idEvento"));

        Evento eventoOBJ = new Evento();
        eventoOBJ.setIdEvento(idEvento);

        EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
        eventoInscricaoOBJ.setEvento(eventoOBJ);

        EventoInscricaoDAO eventoInscricaoDAO = new EventoInscricaoDAO();
        ArrayList<EventoInscricao> eventoInscricaoList = eventoInscricaoDAO.listarTodosInscritos(eventoInscricaoOBJ);

        if (eventoInscricaoList.size() > 0) {
            for (EventoInscricao eventoInscricao : eventoInscricaoList) {
                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(eventoInscricao.getSocio().getIdSocio());

                SocioDAO socioDAO = new SocioDAO();
                socioOBJ = socioDAO.consultarPorId(socioOBJ);

                eventoInscricao.setSocio(socioOBJ);
            }

            req.setAttribute("listaInscrito", eventoInscricaoList);
            req.setAttribute("eventoInscrito", eventoOBJ);
            req.setAttribute("msg", "Resultado: " + eventoInscricaoList.size() + " inscrito(s)!");
        } else {
            pagina = "/AtendimentoController?action=Evento.ListaEvento";

            req.setAttribute("msg", "Evento ainda não possui inscritos!");
        }

        return pagina;
    }

}
