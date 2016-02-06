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

public class PesquisaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "atendimento/socioListar.jsp";

        String nomeSocio = req.getParameter("nomeSocio");

        Socio socioOBJ = new Socio();
        socioOBJ.setNomeSocio(nomeSocio);

        SocioDAO socioDAO = new SocioDAO();
        ArrayList<Socio> socioList = socioDAO.pesquisar(socioOBJ);

        for (Socio socio : socioList) {
            CategoriaSocio categoriaSocio = new CategoriaSocio();
            categoriaSocio.setIdCategoriaSocio(socio.getCategoriaSocio().getIdCategoriaSocio());

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(socio.getUsuarioSocio().getIdUsuario());

            CategoriaSocioDAO categoriaSocioDAO = new CategoriaSocioDAO();
            categoriaSocio = categoriaSocioDAO.consultarPorId(categoriaSocio);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuario = usuarioDAO.consultarPorId(usuario);

            socio.setCategoriaSocio(categoriaSocio);
            socio.setUsuarioSocio(usuario);
        }

        req.setAttribute("listaSocio", socioList);
        req.setAttribute("msg", "Resultado: " + socioList.size() + " sócio(s)!");

        return pagina;
    }
}
