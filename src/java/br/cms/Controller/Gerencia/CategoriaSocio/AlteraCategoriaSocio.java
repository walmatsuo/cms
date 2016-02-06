package br.cms.Controller.Gerencia.CategoriaSocio;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlteraCategoriaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/categoriaForm.jsp";
        
        long idCategoriaSocio = Long.parseLong(req.getParameter("idCategoriaSocio"));

        CategoriaSocio categoriaOBJ = new CategoriaSocio();
        categoriaOBJ.setIdCategoriaSocio(idCategoriaSocio);

        CategoriaSocioDAO categoriaDAO = new CategoriaSocioDAO();
        categoriaOBJ = categoriaDAO.consultarPorId(categoriaOBJ);

        req.setAttribute("categoria", categoriaOBJ);

        return pagina;
    }
}
