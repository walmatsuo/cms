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

public class ListaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "atendimento/socioListar.jsp";

        CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
        categoriaSocioOBJ.setStatusCategoria("a");

        CategoriaSocioDAO categoriaSocioDAO = new CategoriaSocioDAO();
        int qtdeCategoria = categoriaSocioDAO.retornarQuantidadePorStatus(categoriaSocioOBJ);

        if (qtdeCategoria > 0) {
            SocioDAO socioDAO = new SocioDAO();
            ArrayList<Socio> socioList = socioDAO.listarTodos();

            for (Socio socio : socioList) {
                CategoriaSocio categoriaSocio = new CategoriaSocio();
                categoriaSocio.setIdCategoriaSocio(socio.getCategoriaSocio().getIdCategoriaSocio());

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(socio.getUsuarioSocio().getIdUsuario());

                categoriaSocio = categoriaSocioDAO.consultarPorId(categoriaSocio);

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuario = usuarioDAO.consultarPorId(usuario);

                socio.setCategoriaSocio(categoriaSocio);
                socio.setUsuarioSocio(usuario);
            }

            req.setAttribute("listaSocio", socioList);
            
            if (req.getAttribute("msg") == null) {
                req.setAttribute("msg", "Resultado: " + socioList.size() + " sócio(s)!");
            }

        } else {
            pagina = "principal.jsp";
            req.setAttribute("msg", "* Categoria Sócio: Solicite à Gerencia, o cadastro das categorias de sócio!");
        }

        return pagina;
    }
}
