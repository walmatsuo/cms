package br.cms.Controller.Gerencia.CategoriaSocio;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaCategoriaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/categoriaListar.jsp";

        CategoriaSocioDAO categoriaDAO = new CategoriaSocioDAO();
        ArrayList<CategoriaSocio> categoriaList = categoriaDAO.listarTodos();

        req.setAttribute("listaCategoria", categoriaList);

        if (req.getAttribute("msg") == null) {
            req.setAttribute("msg", "Resultado: " + categoriaList.size() + " categoria(s)!");
        }

        return pagina;
    }
}
