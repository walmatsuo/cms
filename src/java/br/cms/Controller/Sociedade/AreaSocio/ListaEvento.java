package br.cms.Controller.Sociedade.AreaSocio;

import br.cms.Model.Evento;
import br.cms.DAO.EventoDAO;
import br.cms.Interface.Executa;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaEvento implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/eventoListar.jsp";

        EventoDAO eventoDAO = new EventoDAO();
        ArrayList<Evento> eventoList = eventoDAO.listarTodos();

        req.setAttribute("listaEvento", eventoList);

        if (req.getAttribute("msg") == null) {
            req.setAttribute("msg", "Resultado: " + eventoList.size() + " evento(s)!");
        }
        
        return pagina;
    }

}
