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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class NotificaFilho18AnosJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        int idadeLimite = 18;
        int diasAntecedencia = 30; // Quantidade dias para avisar com antecedência
        String parentescoSt = "filho";

        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setStatusDependente("a");

        DependenteDAO dependenteDAO = new DependenteDAO();
        ArrayList<Dependente> dependenteList = dependenteDAO.listarTodosPorStatus(dependenteOBJ);

        ArrayList<Dependente> dependenteFilho18List = new ArrayList<Dependente>();

        Calendar dataAtualCalendar = Calendar.getInstance();
        dataAtualCalendar.add(Calendar.DAY_OF_MONTH, diasAntecedencia);

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
            Calendar dataAniversarioCal = Calendar.getInstance();
            dataAniversarioCal.setTime(dependente.getNascimentoDependente());
            dataAniversarioCal.add(Calendar.YEAR, idadeLimite);

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String dataAniversarioSt = df.format(dataAniversarioCal.getTime());

            Socio socio = dependente.getSocioDependente();

            SocioDAO socioDAO = new SocioDAO();
            socio = socioDAO.consultarPorId(socio);

            dependente.setSocioDependente(socio);

            Usuario usuario = socio.getUsuarioSocio();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuario = usuarioDAO.consultarPorId(usuario);

            try {
                String tituloEmail = ("CMS - Alerta Dependente");
                String mensagem = ("<h2> Olá, " + socio.getNomeSocio() + "!</h2>"
                        + "<h3>Seu " + parentescoSt + " " + dependente.getNomeDependente() + " irá completar " + idadeLimite + " anos no dia " + dataAniversarioSt + ". </h3>"
                        + "<h3>E conforme consta no Estatuto do Clube, o mesmo não poderá ser mais seu dependente e será bloqueado na data acima.</h3>"
                        + "<h3>* Caso ele queira adquirir um novo título, dirija-se ao Atendimento.</h3>");

                EmailMensagem em = new EmailMensagem(usuario.getEmail(), tituloEmail, mensagem, usuario.getNomeUsuario());

                GerenciadorEmail ge = new GerenciadorEmail();
                ge.enviarEmailMensagem(em);
            } catch (Exception e) {
                System.out.println("Servidor sem Internet, notifique o TI. Não foi possível notificar o Sócio: " + socio.getNomeSocio() + " sobre o dependente " + dependente.getNomeDependente() + ".");

            }
        }
    }
}
