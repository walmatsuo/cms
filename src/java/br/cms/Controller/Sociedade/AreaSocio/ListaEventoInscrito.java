package br.cms.Controller.Sociedade.AreaSocio;

import br.cms.DAO.EventoInscricaoDAO;
import br.cms.Model.EventoInscricao;
import br.cms.Model.Evento;
import br.cms.DAO.EventoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListaEventoInscrito implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/eventoInscritoListar.jsp";

        HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
        Usuario usuarioOBJ = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

        long idSocio = usuarioOBJ.getLogin();

        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(idSocio);

        EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
        eventoInscricaoOBJ.setSocio(socioOBJ);

        EventoInscricaoDAO eventoInscricaoDAO = new EventoInscricaoDAO();
        ArrayList<EventoInscricao> inscricaoEventoList = eventoInscricaoDAO.listarTodosEventos(eventoInscricaoOBJ);

        for (EventoInscricao eventoInscricao : inscricaoEventoList) {
            Evento evento = new Evento();
            evento.setIdEvento(eventoInscricao.getEvento().getIdEvento());

            EventoDAO eventoDAO = new EventoDAO();
            evento = eventoDAO.consultarPorId(evento);

            eventoInscricao.setEvento(evento);
        }

        req.setAttribute("listaEvento", inscricaoEventoList);
        req.setAttribute("socioInscrito", socioOBJ);
        req.setAttribute("msg", "Resultado: " + inscricaoEventoList.size() + " evento(s)!");

        return pagina;
    }

}
