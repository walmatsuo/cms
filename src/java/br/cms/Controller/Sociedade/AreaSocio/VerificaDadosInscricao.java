package br.cms.Controller.Sociedade.AreaSocio;

import br.cms.Model.Evento;
import br.cms.DAO.EventoDAO;
import br.cms.DAO.EventoInscricaoDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.EventoInscricao;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerificaDadosInscricao implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/confirmarInscricao.jsp";

        HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
        Usuario usuarioOBJ = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

        long idSocio = usuarioOBJ.getLogin();
        long idEvento = Long.parseLong(req.getParameter("idEvento"));

        //Recuperando Evento
        Evento eventoOBJ = new Evento();
        eventoOBJ.setIdEvento(idEvento);

        EventoDAO eventoDAO = new EventoDAO();
        eventoOBJ = eventoDAO.consultarPorId(eventoOBJ);

        //Recuperando Socio
        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(idSocio);

        SocioDAO socioDAO = new SocioDAO();
        socioOBJ = socioDAO.consultarPorId(socioOBJ);

        //Teste
        EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
        eventoInscricaoOBJ.setEvento(eventoOBJ);

        EventoInscricaoDAO eventoInscricaoDAO = new EventoInscricaoDAO();
        ArrayList<EventoInscricao> eventoInscricaoList = eventoInscricaoDAO.listarTodosInscritos(eventoInscricaoOBJ);

        String status = null;
        
        for (EventoInscricao eventoInscricao : eventoInscricaoList) {
            if (eventoInscricao.getSocio().getIdSocio() == idSocio) {
                status = "a";
            }
        }

        req.setAttribute("evento", eventoOBJ);
        req.setAttribute("socio", socioOBJ);
        req.setAttribute("status", status);

        return pagina;
    }

}
