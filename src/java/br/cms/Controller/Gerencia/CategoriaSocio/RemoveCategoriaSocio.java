package br.cms.Controller.Gerencia.CategoriaSocio;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import br.cms.Model.Socio;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveCategoriaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "GerenciaController?action=CategoriaSocio.ListaCategoriaSocio";

        long idCategoriaSocio = Long.parseLong(req.getParameter("idCategoriaSocio"));

        CategoriaSocio categoriaOBJ = new CategoriaSocio();
        categoriaOBJ.setIdCategoriaSocio(idCategoriaSocio);
        categoriaOBJ.setStatusCategoria("x");

        Socio socioOBJ = new Socio();
        socioOBJ.setCategoriaSocio(categoriaOBJ);

        SocioDAO socioDAO = new SocioDAO();
        int sociosPorCategoria = socioDAO.retornarQuantidadePorCategoria(socioOBJ);

        if (sociosPorCategoria == 0) {
            CategoriaSocioDAO categoriaDAO = new CategoriaSocioDAO();
            categoriaDAO.alterarStatus(categoriaOBJ);

            req.setAttribute("msg", "Categoria excluída com sucesso!");
        } else {
            req.setAttribute("msg", "Categoria não pode ser excluída, pois possui " + sociosPorCategoria + " sócio(s) vinculado(s) à ela!");
        }

        return pagina;
    }
}
