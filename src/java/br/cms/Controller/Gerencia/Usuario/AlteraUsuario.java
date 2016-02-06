package br.cms.Controller.Gerencia.Usuario;

import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlteraUsuario implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/usuarioForm.jsp";
        
        long idUsuario = Long.parseLong(req.getParameter("idUsuario"));

        Usuario usuarioOBJ = new Usuario();
        usuarioOBJ.setIdUsuario(idUsuario);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioOBJ = usuarioDAO.consultarPorId(usuarioOBJ);

        req.setAttribute("usuario", usuarioOBJ);

        return pagina;
    }
}
