package br.cms.Controller.Acesso;

import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Usuario;
import br.cms.Email.EmailMensagem;
import br.cms.Senha.Criptografia;
import br.cms.Senha.GeradorSenha;
import br.cms.Email.GerenciadorEmail;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;

public class EnviaSenha implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "index.jsp";

        String login = req.getParameter("login");
        String email = req.getParameter("email");

        if (login.isEmpty()) {
            login = "0";
        }

        Usuario usuarioOBJ = new Usuario();
        usuarioOBJ.setLogin(Long.parseLong(login));
        usuarioOBJ.setEmail(email);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioOBJ = usuarioDAO.consultarPorLoginEmail(usuarioOBJ);

        String senha = GeradorSenha.gerarSenha();
        String senhaCriptografada = Criptografia.criptografaSenha(senha);
        
        if (usuarioOBJ != null) {
            try {
                // Enviando e-mail de reset de senha
                String tituloEmail = ("CMS - Senha recuperada !");
                String mensagem = ("<h2>" + usuarioOBJ.getNomeUsuario() + " !</h2>"
                        + "<h3>Foi solicitada a recuperação da sua senha.</h3>"
                        + "<h3>LOGIN: " + usuarioOBJ.getLogin() + "</h3>"
                        + "<h3>SENHA: " + senha + "</h3>");

                EmailMensagem em = new EmailMensagem(usuarioOBJ.getEmail(), tituloEmail, mensagem, usuarioOBJ.getNomeUsuario());

                GerenciadorEmail ge = new GerenciadorEmail();

                ge.enviarEmailMensagem(em);
                // Fim do envio do e-mail
                
                usuarioOBJ.setSenha(senhaCriptografada);
                usuarioOBJ.setStatusUsuario("p");

                usuarioDAO.alterarSenhaStatus(usuarioOBJ);
            } catch (Exception e) {
                throw new EmailException("Servidor sem Internet, notifique o TI! Senha não foi alterada.");
            }
        }

        req.setAttribute("msg", "Senha enviada no e-mail cadastrado!");

        return pagina;
    }
}
