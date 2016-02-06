package br.cms.Controller.Atendimento.Socio;

import br.cms.DAO.SocioDAO;
import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import br.cms.Model.CategoriaUsuario;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import br.cms.Email.EmailMensagem;
import br.cms.Senha.Criptografia;
import br.cms.Senha.GeradorSenha;
import br.cms.Email.GerenciadorEmail;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;

public class SalvaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "AtendimentoController?action=Socio.ListaSocio";

        String idSocio = req.getParameter("idSocio");
        String senha = GeradorSenha.gerarSenha();
        String senhaCriptografada = Criptografia.criptografaSenha(senha);
        String nomeSocio = req.getParameter("nomeSocio");
        String cpfSocio = req.getParameter("cpfSocio");
        String logradouro = req.getParameter("logradouro");
        String numero = req.getParameter("numero");
        String complemento = req.getParameter("complemento");
        String bairro = req.getParameter("bairro");
        String cep = req.getParameter("cep");
        String municipio = req.getParameter("municipio");
        String uf = req.getParameter("uf");
        String nascimentoSocioSt = req.getParameter("nascimentoSocio");

        Date nascimentoSocio = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            nascimentoSocio = df.parse(nascimentoSocioSt);
        } catch (ParseException e) {
            throw new Exception("Data inválida: " + nascimentoSocioSt + ", tente novamente com uma data válida.");
        }

        String sexoSocio = req.getParameter("sexoSocio");
        String nacionalidadeSocio = req.getParameter("nacionalidadeSocio");
        String naturalidadeSocio = req.getParameter("naturalidadeSocio");
        String estadoCivilSocio = req.getParameter("estadoCivilSocio");
        String profissao = req.getParameter("profissao");
        String telefone1 = req.getParameter("telefone1");
        String telefone2 = req.getParameter("telefone2");
        String telefone3 = req.getParameter("telefone3");
        Date inclusaoSocio = new Date();

        long idCategoriaSocio = Long.parseLong(req.getParameter("categoriaSocio"));
        String emailDestinatario = req.getParameter("email");

        if (idSocio.equals("")) {
            Usuario usuarioOBJ = new Usuario();
            usuarioOBJ.setSenha(senhaCriptografada);
            usuarioOBJ.setNomeUsuario(nomeSocio);
            usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.Sociedade);
            usuarioOBJ.setEmail(emailDestinatario);
            usuarioOBJ.setStatusUsuario("b");

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            long usuarioSocioId = usuarioDAO.cadastrarSocio(usuarioOBJ);

            usuarioOBJ.setIdUsuario(usuarioSocioId);

            CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
            categoriaSocioOBJ.setIdCategoriaSocio(idCategoriaSocio);

            Socio socioOBJ = new Socio();
            socioOBJ.setNomeSocio(nomeSocio);
            socioOBJ.setCpfSocio(cpfSocio);
            socioOBJ.setLogradouro(logradouro);
            socioOBJ.setNumero(numero);
            socioOBJ.setComplemento(complemento);
            socioOBJ.setBairro(bairro);
            socioOBJ.setCep(cep);
            socioOBJ.setMunicipio(municipio);
            socioOBJ.setUf(uf);
            socioOBJ.setNascimentoSocio(nascimentoSocio);
            socioOBJ.setSexoSocio(sexoSocio);
            socioOBJ.setNacionalidadeSocio(nacionalidadeSocio);
            socioOBJ.setNaturalidadeSocio(naturalidadeSocio);
            socioOBJ.setEstadoCivilSocio(estadoCivilSocio);
            socioOBJ.setProfissao(profissao);
            socioOBJ.setTelefone1(telefone1);
            socioOBJ.setTelefone2(telefone2);
            socioOBJ.setTelefone3(telefone3);
            socioOBJ.setInclusaoSocio(inclusaoSocio);
            socioOBJ.setStatusSocio("b");
            socioOBJ.setCategoriaSocio(categoriaSocioOBJ);
            socioOBJ.setUsuarioSocio(usuarioOBJ);

            // Cadastando Sócio e recuperando o ID dele (Nº Título)
            SocioDAO socioDAO = new SocioDAO();
            long loginSocio = socioDAO.cadastrar(socioOBJ);

            // Setando o ID do sócio como Login do usuário
            usuarioOBJ.setLogin(loginSocio);
            usuarioDAO.alterarLogin(usuarioOBJ);

            // Comunicando Sócio sobre seu cadastro
            try {
                String tituloEmail = ("Bem vindo ao CMS !");
                String mensagem = ("<h2>Olá " + socioOBJ.getNomeSocio() + " !</h2>"
                        + "<h3>Você acaba de ser cadastrado(a) em nosso sistema! </h3>"
                        + "<h3>TÍTULO: " + loginSocio + "</h3>"
                        + "<h3>* Seu cadastro será ativado assim que o pagamento do título for confirmado.</h3>");

                EmailMensagem em = new EmailMensagem(emailDestinatario, tituloEmail, mensagem, nomeSocio);

                GerenciadorEmail ge = new GerenciadorEmail();
                ge.enviarEmailMensagem(em);
            } catch (Exception e) {
                throw new EmailException("Servidor sem Internet, notifique o TI! Sócio(a) " + socioOBJ.getNomeSocio() + " cadastrado(a). (Título Nº: " + loginSocio + ")");
            }

            req.setAttribute("msg", "Sócio(a) " + socioOBJ.getNomeSocio() + " cadastrado(a) com sucesso! Login enviado via e-mail.");

        } else {
            Socio socioOBJ = new Socio();
            socioOBJ.setIdSocio(Long.parseLong(idSocio));

            SocioDAO socioDAO = new SocioDAO();
            socioOBJ = socioDAO.consultarPorId(socioOBJ);

            Usuario usuarioOBJ = socioOBJ.getUsuarioSocio();
            usuarioOBJ.setNomeUsuario(nomeSocio);
            usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.Sociedade);
            usuarioOBJ.setEmail(emailDestinatario);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.alterar(usuarioOBJ);

            CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
            categoriaSocioOBJ.setIdCategoriaSocio(idCategoriaSocio);

            socioOBJ.setNomeSocio(nomeSocio);
            socioOBJ.setLogradouro(logradouro);
            socioOBJ.setNumero(numero);
            socioOBJ.setComplemento(complemento);
            socioOBJ.setBairro(bairro);
            socioOBJ.setCep(cep);
            socioOBJ.setMunicipio(municipio);
            socioOBJ.setUf(uf);
            socioOBJ.setNascimentoSocio(nascimentoSocio);
            socioOBJ.setSexoSocio(sexoSocio);
            socioOBJ.setNacionalidadeSocio(nacionalidadeSocio);
            socioOBJ.setNaturalidadeSocio(naturalidadeSocio);
            socioOBJ.setEstadoCivilSocio(estadoCivilSocio);
            socioOBJ.setProfissao(profissao);
            socioOBJ.setTelefone1(telefone1);
            socioOBJ.setTelefone2(telefone2);
            socioOBJ.setTelefone3(telefone3);
            socioOBJ.setCategoriaSocio(categoriaSocioOBJ);

            // Atualizando Sócio
            socioDAO.alterar(socioOBJ);

            req.setAttribute("msg", "Sócio(a) " + socioOBJ.getNomeSocio() + " atualizado(a) com sucesso!");
        }

        return pagina;
    }
}
