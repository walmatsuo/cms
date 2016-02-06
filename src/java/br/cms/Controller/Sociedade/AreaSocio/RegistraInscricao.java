package br.cms.Controller.Sociedade.AreaSocio;

import br.cms.DAO.EventoInscricaoDAO;
import br.cms.Model.EventoInscricao;
import br.cms.Model.Evento;
import br.cms.Interface.Executa;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistraInscricao implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "SociedadeController?action=AreaSocio.ListaEvento";

        long idEvento = Long.parseLong(req.getParameter("idEvento"));

        HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
        Usuario usuarioOBJ = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

        long idSocio = usuarioOBJ.getLogin();

        Evento eventoOBJ = new Evento();
        eventoOBJ.setIdEvento(idEvento);

        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(idSocio);

        EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
        eventoInscricaoOBJ.setEvento(eventoOBJ);
        eventoInscricaoOBJ.setSocio(socioOBJ);
        eventoInscricaoOBJ.setStatusEventoInscricao("a");

        EventoInscricaoDAO eventoInscricaoDAO = new EventoInscricaoDAO();
        eventoInscricaoDAO.cadastrar(eventoInscricaoOBJ);

        return pagina;
    }

}
