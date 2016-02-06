package br.cms.Controller.Gerencia.Relatorio;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PreparaSocioPorCategoria implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/relatorioSocioPorCategoria.jsp";
        
        CategoriaSocio categoriaOBJ = new CategoriaSocio();
        categoriaOBJ.setStatusCategoria("a");

        CategoriaSocioDAO categoriaSocioDAO = new CategoriaSocioDAO();
        int qtdeCategoria = categoriaSocioDAO.retornarQuantidadePorStatus(categoriaOBJ);

        if (qtdeCategoria > 0) {
            ArrayList<CategoriaSocio> categoriaSocioList = categoriaSocioDAO.listarTodos();

            req.setAttribute("listaCategoriaSocio", categoriaSocioList);
        } else {
            pagina = "principal.jsp";
            req.setAttribute("msg", "* Categoria Sócio: Solicite à Gerencia, o cadastro das categorias de sócio!");
        }
        return pagina;
    }
}
