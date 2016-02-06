package br.cms.Quartz;

import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.DAO.SocioDAO;
import br.cms.DAO.UsuarioDAO;
import br.cms.Email.EmailMensagem;
import br.cms.Email.GerenciadorEmail;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BloqueiaFilho18AnosJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        int idadeLimite = 18;
        String parentescoSt = "filho";

        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setStatusDependente("a");

        DependenteDAO dependenteDAO = new DependenteDAO();
        ArrayList<Dependente> dependenteList = dependenteDAO.listarTodosPorStatus(dependenteOBJ);

        ArrayList<Dependente> dependenteFilho18List = new ArrayList<Dependente>();

        Calendar dataAtualCalendar = Calendar.getInstance();

        for (Dependente dependente : dependenteList) {

            Parentesco parentesco = dependente.getParentescoDependente();

            ParentescoDAO parentescoDAO = new ParentescoDAO();
            parentesco = parentescoDAO.consultarPorId(parentesco);

            dependente.setParentescoDependente(parentesco);

            // Data de nascimento (Calendar)
            Date dataNascimentoDate = dependente.getNascimentoDependente();
            Calendar dataNascimentoCal = Calendar.getInstance();
            dataNascimentoCal.setTime(dataNascimentoDate);

            // Data que irá completar a idade indicada (Calendar)
            Calendar dataAniversarioCal = (Calendar) dataNascimentoCal.clone();
            dataAniversarioCal.add(Calendar.YEAR, idadeLimite);

            if (dependente.getSexoDependente().equalsIgnoreCase("m")) {
                if (dependente.getParentescoDependente().getNomeParentesco().equalsIgnoreCase(parentescoSt)) {
                    if (dataAtualCalendar.after(dataAniversarioCal)) {
                        dependenteFilho18List.add(dependente);
                    }
                }
            }
        }

        for (Dependente dependente : dependenteFilho18List) {
            Socio socio = dependente.getSocioDependente();

            SocioDAO socioDAO = new SocioDAO();
            socio = socioDAO.consultarPorId(socio);

            dependente.setSocioDependente(socio);

            Usuario usuario = socio.getUsuarioSocio();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuario = usuarioDAO.consultarPorId(usuario);

            dependente.setStatusDependente("b");

            dependenteDAO.alterarStatus(dependente);

            try {
                String tituloEmail = ("CMS - Dependente Bloqueado");
                String mensagem = ("<h2> Olá, " + socio.getNomeSocio() + "!</h2>"
                        + "<h3>Notificamos que conforme está no Estatuto, seu " + parentescoSt + " " + dependente.getNomeDependente() + " está bloqueado, pois possui " + idadeLimite + " anos. </h3>"
                        + "<h3>* Caso o mesmo queira adquirir um novo título, dirija-se ao Atendimento.</h3>");

                EmailMensagem em = new EmailMensagem(usuario.getEmail(), tituloEmail, mensagem, usuario.getNomeUsuario());

                GerenciadorEmail ge = new GerenciadorEmail();
                ge.enviarEmailMensagem(em);
            } catch (Exception e) {
                System.out.println("Servidor sem Internet, notifique o TI.");
            }
        }
    }
}
