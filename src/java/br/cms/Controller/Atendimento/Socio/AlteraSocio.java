package br.cms.Controller.Atendimento.Socio;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.DAO.SocioDAO;
import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlteraSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "atendimento/socioForm.jsp";

        CategoriaSocio categoriaOBJ = new CategoriaSocio();
        categoriaOBJ.setStatusCategoria("a");

        CategoriaSocioDAO categoriaSocioDAO = new CategoriaSocioDAO();
        int qtdeCategoria = categoriaSocioDAO.retornarQuantidadePorStatus(categoriaOBJ);

        // Verificando se existe ao menos UMA Categoria Sócio cadastrada
        if (qtdeCategoria > 0) {
            long idSocio = Long.parseLong(req.getParameter("idSocio"));

            Socio socioOBJ = new Socio();
            socioOBJ.setIdSocio(idSocio);

            SocioDAO socioDAO = new SocioDAO();
            socioOBJ = socioDAO.consultarPorId(socioOBJ);

            CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
            categoriaSocioOBJ.setIdCategoriaSocio(socioOBJ.getCategoriaSocio().getIdCategoriaSocio());

            Usuario usuarioOBJ = new Usuario();
            usuarioOBJ.setIdUsuario(socioOBJ.getUsuarioSocio().getIdUsuario());

            categoriaSocioOBJ = categoriaSocioDAO.consultarPorId(categoriaSocioOBJ);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioOBJ = usuarioDAO.consultarPorId(usuarioOBJ);

            socioOBJ.setCategoriaSocio(categoriaSocioOBJ);
            socioOBJ.setUsuarioSocio(usuarioOBJ);

            ArrayList<CategoriaSocio> categoriaSocioList = categoriaSocioDAO.listarTodos();

            req.setAttribute("listaCategoriaSocio", categoriaSocioList);
            req.setAttribute("socio", socioOBJ);

        } else {
            pagina = "principal.jsp";
            req.setAttribute("msg", "* Categoria Sócio: Solicite à Gerencia, o cadastro das categorias de sócio!");
        }

        return pagina;
    }
}
