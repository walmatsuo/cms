package br.cms.Controller.Acesso;

import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Usuario;
import br.cms.Senha.Criptografia;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DefineNovaSenha implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = null;
        
        String novaSenha = req.getParameter("senha");
        String novaSenhaConfirm = req.getParameter("senhaConfirm");

        // Recuperando Usuario logado da Sessão
        HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
        Usuario usuarioAutenticado = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

        if (novaSenha.equals(novaSenhaConfirm)) {
            String senhaCriptografada = Criptografia.criptografaSenha(novaSenha);
            
            usuarioAutenticado.setSenha(senhaCriptografada);
            usuarioAutenticado.setStatusUsuario("a");

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.alterarSenhaStatus(usuarioAutenticado);

            sessaoUsuario.setAttribute("usuarioAutenticado", usuarioAutenticado);

            pagina = "principal.jsp";
        } else {
            pagina = "alterarSenha.jsp";
        }

        return pagina;
    }
}
