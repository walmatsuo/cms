package br.cms.Controller.Gerencia.Usuario;

import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaUsuario;
import br.cms.Model.Usuario;
import br.cms.Email.EmailMensagem;
import br.cms.Senha.Criptografia;
import br.cms.Senha.GeradorSenha;
import br.cms.Email.GerenciadorEmail;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;

public class SalvaUsuario implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "GerenciaController?action=Usuario.ListaUsuario";

        String idUsuario = req.getParameter("idUsuario");
        String senha = GeradorSenha.gerarSenha();
        String senhaCriptografada = Criptografia.criptografaSenha(senha);
        String nomeUsuario = req.getParameter("nomeUsuario");
        String emailDestinatario = req.getParameter("email");
        String categoriaUsuario = req.getParameter("categoriaUsuario");

        if (idUsuario.equals("")) {
            Usuario usuarioOBJ = new Usuario();
            usuarioOBJ.setSenha(senhaCriptografada);
            usuarioOBJ.setNomeUsuario(nomeUsuario);
            usuarioOBJ.setEmail(emailDestinatario);
            usuarioOBJ.setStatusUsuario("p");

            if (categoriaUsuario.equals("Atendimento")) {
                usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.Atendimento);
            } else {
                usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.Gerencia);
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            long usuarioFunc = usuarioDAO.cadastrarFuncionario(usuarioOBJ);

            try {
                String tituloEmail = ("Bem vindo ao CMS !");
                String mensagem = ("<h2>" + usuarioOBJ.getNomeUsuario() + ", bem-vindo(a)!</h2>"
                        + "<h3>Utilize os seguintes dados para se conectar:</h3>"
                        + "<h3>LOGIN: " + usuarioFunc + "</h3>"
                        + "<h3>SENHA: " + senha + "</h3>"
                        + "<h3>* Será solicitado a troca da senha no primeiro acesso.</h3>");

                EmailMensagem em = new EmailMensagem(emailDestinatario, tituloEmail, mensagem, nomeUsuario);

                GerenciadorEmail ge = new GerenciadorEmail();
                ge.enviarEmailMensagem(em);
            } catch (Exception e) {
                throw new EmailException("Servidor sem Internet, notifique o TI. Usuário " + usuarioOBJ.getNomeUsuario() + " cadastrado (Login: " + usuarioFunc + " | Senha: " + senha + ").");
            }

            req.setAttribute("msg", "Usuário " + usuarioOBJ.getNomeUsuario() + " cadastrado com sucesso! Login e senha enviados via e-mail.");
        } else {
            // Caso Categoria seja Gerencia, verificar abaixo se não é o Único ativo
            boolean alteraUsuario = true;

            Usuario usuarioOBJ = new Usuario();
            usuarioOBJ.setIdUsuario(Long.parseLong(idUsuario));

            // Recuperando dados Usuário selecionado
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioOBJ = usuarioDAO.consultarPorId(usuarioOBJ);

            if (usuarioOBJ.getCategoriaUsuario().equals(CategoriaUsuario.Gerencia)) {
                // Recuperando quantidade Usuários Gerencia Ativos
                int qtdeGerenteAtivo = usuarioDAO.retornarQuantidadePorCategoria(usuarioOBJ);

                // Verificando se existe pelo menos UM Usuário Gerencia Ativo e Bloqueando alteração de CategoriaUsuario
                if ((qtdeGerenteAtivo == 1) && (categoriaUsuario.equals("Atendimento"))) {
                    alteraUsuario = false;
                }

            }

            if (alteraUsuario) {
                usuarioOBJ.setNomeUsuario(nomeUsuario);
                usuarioOBJ.setEmail(emailDestinatario);

                if (categoriaUsuario.equals("Atendimento")) {
                    usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.Atendimento);
                } else {
                    usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.Gerencia);
                }

                usuarioDAO.alterar(usuarioOBJ);

                req.setAttribute("msg", "Usuário " + usuarioOBJ.getNomeUsuario() + " atualizado com sucesso!");
            } else {
                req.setAttribute("msg", "Usuário " + usuarioOBJ.getNomeUsuario() + " não pode ser do Atendimento, pois é o único gerente do sistema!");
            }

        }

        return pagina;
    }
}
