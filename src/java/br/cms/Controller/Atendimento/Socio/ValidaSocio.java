package br.cms.Controller.Atendimento.Socio;

import br.cms.DAO.SocioDAO;
import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import br.cms.Email.EmailMensagem;
import br.cms.Senha.Criptografia;
import br.cms.Senha.GeradorSenha;
import br.cms.Email.GerenciadorEmail;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;

public class ValidaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "AtendimentoController?action=Socio.ListaSocio";

        long idSocio = Long.parseLong(req.getParameter("idSocio"));
        String senha = GeradorSenha.gerarSenha();
        String senhaCriptografada = Criptografia.criptografaSenha(senha);

        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(idSocio);
        socioOBJ.setStatusSocio("a");
        
        SocioDAO socioDAO = new SocioDAO();
        socioDAO.alterarStatus(socioOBJ);
        
        socioOBJ = socioDAO.consultarPorId(socioOBJ);

        Usuario usuarioOBJ = new Usuario();
        usuarioOBJ.setIdUsuario(socioOBJ.getUsuarioSocio().getIdUsuario());
        usuarioOBJ.setSenha(senhaCriptografada);
        usuarioOBJ.setStatusUsuario("p");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.alterarSenhaStatus(usuarioOBJ);
        usuarioOBJ = usuarioDAO.consultarPorId(usuarioOBJ);

        try {
            String tituloEmail = ("Agora você é CMS !");
            String mensagem = ("<h2>Olá " + socioOBJ.getNomeSocio() + "!</h2>"
                    + "<h3>O pagamento do seu título acaba de ser confirmado!</h3>"
                    + "<h3>LOGIN: " + socioOBJ.getIdSocio() + "</h3>"
                    + "<h3>SENHA: " + senha + "</h3>"
                    + "<h3>* Será solicitada a alteração da sua senha no primeiro acesso.</h3>");

            EmailMensagem em = new EmailMensagem(usuarioOBJ.getEmail(), tituloEmail, mensagem, socioOBJ.getNomeSocio());

            GerenciadorEmail ge = new GerenciadorEmail();
            ge.enviarEmailMensagem(em);
        } catch (Exception e) {
            throw new EmailException("Servidor sem Internet, notifique o TI! Sócio " + socioOBJ.getNomeSocio() + " ativado. (Login: " + socioOBJ.getIdSocio() + " | Senha: " + senha + ")");
        }

        req.setAttribute("msg", "Sócio(a) " + socioOBJ.getNomeSocio() + " ativado(a) com sucesso! Login e senha enviados via e-mail.");

        return pagina;
    }
}
