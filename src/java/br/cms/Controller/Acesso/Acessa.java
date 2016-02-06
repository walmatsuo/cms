package br.cms.Controller.Acesso;

import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Usuario;
import br.cms.Senha.Criptografia;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Acessa implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = null;
        
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");

        if (login.isEmpty()) {
            login = "0";
        }

        String senhaCriptografada = Criptografia.criptografaSenha(senha);
        
        Usuario usuarioOBJ = new Usuario();
        usuarioOBJ.setLogin(Long.parseLong(login));
        usuarioOBJ.setSenha(senhaCriptografada);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioAutenticado = usuarioDAO.autenticarUsuario(usuarioOBJ);

        if (usuarioAutenticado != null) {
            HttpSession sessaoUsuario = req.getSession();
            sessaoUsuario.setAttribute("usuarioAutenticado", usuarioAutenticado);

            pagina = "principal.jsp";
        } else {
            req.setAttribute("msg", "Login ou Senha Incorreto!");

            pagina = "index.jsp";
        }

        return pagina;
    }
}
