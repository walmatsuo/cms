package br.cms.Controller.Gerencia.CategoriaSocio;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PesquisaCategoriaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/categoriaListar.jsp";
        
        String nomeCategoria = req.getParameter("nomeCategoria");

        CategoriaSocio categoriaOBJ = new CategoriaSocio();
        categoriaOBJ.setNomeCategoriaSocio(nomeCategoria);

        CategoriaSocioDAO categoriaDAO = new CategoriaSocioDAO();
        ArrayList<CategoriaSocio> categoriaList = categoriaDAO.pesquisar(categoriaOBJ);

        req.setAttribute("listaCategoria", categoriaList);
        req.setAttribute("msg", "Resultado: " + categoriaList.size() + " categoria(s)!");

        return pagina;
    }
}
