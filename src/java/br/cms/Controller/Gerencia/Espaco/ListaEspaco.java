package br.cms.Controller.Gerencia.Espaco;

import br.cms.DAO.EspacoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Espaco;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaEspaco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/espacoListar.jsp";

        EspacoDAO espacoDAO = new EspacoDAO();
        ArrayList<Espaco> espacoList = espacoDAO.listarTodos();

        req.setAttribute("listaEspaco", espacoList);

        if (req.getAttribute("msg") == null) {
            req.setAttribute("msg", "Resultado: " + espacoList.size() + " espaço(s)!");
        }

        return pagina;
    }
}
