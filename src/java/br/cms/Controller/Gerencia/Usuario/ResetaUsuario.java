package br.cms.Controller.Gerencia.Usuario;

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

public class ResetaUsuario implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "GerenciaController?action=Usuario.ListaUsuario";

        long idUsuario = Long.parseLong(req.getParameter("idUsuario"));
        String senha = GeradorSenha.gerarSenha();
        String senhaCriptografada = Criptografia.criptografaSenha(senha);

        Usuario usuarioOBJ = new Usuario();
        usuarioOBJ.setIdUsuario(idUsuario);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioOBJ = usuarioDAO.consultarPorId(usuarioOBJ);

        usuarioOBJ.setSenha(senhaCriptografada);
        usuarioOBJ.setStatusUsuario("p");

        usuarioDAO.alterarSenhaStatus(usuarioOBJ);

        if (usuarioOBJ.getNomeUsuario() != null) {
            try {
                String tituloEmail = ("CMS - Usuário resetado!");
                String mensagem = ("<h2>" + usuarioOBJ.getNomeUsuario() + " !</h2>"
                        + "<h3>Foi solicitado o reset da sua senha.</h3>"
                        + "<h3>LOGIN: " + usuarioOBJ.getLogin() + "</h3>"
                        + "<h3>SENHA: " + senha + "</h3>");

                EmailMensagem em = new EmailMensagem(usuarioOBJ.getEmail(), tituloEmail, mensagem, usuarioOBJ.getNomeUsuario());

                GerenciadorEmail ge = new GerenciadorEmail();
                ge.enviarEmailMensagem(em);
            } catch (Exception e) {
                throw new EmailException("Servidor sem Internet, notifique o TI! Usuário " + usuarioOBJ.getNomeUsuario() + " resetado (Login: " + usuarioOBJ.getLogin() + " | Senha: " + senha + ").");
            }

            req.setAttribute("msg", "Usuário resetado, senha e login enviados no e-mail cadastrado!");
        }

        return pagina;
    }
}
