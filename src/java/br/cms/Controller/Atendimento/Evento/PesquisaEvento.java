package br.cms.Controller.Atendimento.Evento;

import br.cms.DAO.EventoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Evento;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PesquisaEvento implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "atendimento/eventoListar.jsp";

        String nomeEvento = req.getParameter("nomeEvento");

        Evento eventoOBJ = new Evento();
        eventoOBJ.setTitulo(nomeEvento);

        EventoDAO eventoDAO = new EventoDAO();
        ArrayList<Evento> eventoList = eventoDAO.pesquisar(eventoOBJ);

        req.setAttribute("listaEvento", eventoList);
        req.setAttribute("msg", "Resultado: " + eventoList.size() + " evento(s)!");

        return pagina;
    }
}
