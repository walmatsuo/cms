package br.cms.Controller.Sociedade.Dependente;

import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaUsuario;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SalvaDependente implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/dependenteListar.jsp";

        String idDependente = req.getParameter("idDependente");
        String nomeDependente = req.getParameter("nomeDependente");

        // Recuperando e convertendo para Date
        String nascimentoDependenteSt = req.getParameter("nascimentoDependente");

        Date nascimentoDependente = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            nascimentoDependente = df.parse(nascimentoDependenteSt);
        } catch (ParseException e) {
            throw new Exception("Data inválida: " + nascimentoDependenteSt + ", tente novamente com uma data válida.");
        }

        String sexoDependente = req.getParameter("sexoDependente");
        String nacionalidadeDependente = req.getParameter("nacionalidadeDependente");
        String naturalidadeDependente = req.getParameter("naturalidadeDependente");
        String estadoCivilDependente = req.getParameter("estadoCivilDependente");

        // Recuperando Parentesco (id)
        int idParentesco = Integer.parseInt(req.getParameter("parentesco"));
        Parentesco parentescoOBJ = new Parentesco();
        parentescoOBJ.setIdParentesco(idParentesco);

        // Verificando se parentesco for Filho, verificar a idade também (18 anos)
        int idadeLimite = 18;
        String parentescoSt = "filho";
        boolean salvaDependente = true;

        ParentescoDAO parentescoDAO = new ParentescoDAO();
        parentescoOBJ = parentescoDAO.consultarPorId(parentescoOBJ);

        if ((sexoDependente.equalsIgnoreCase("m")) && (parentescoOBJ.getNomeParentesco().equalsIgnoreCase(parentescoSt))) {
            Calendar dataAtualCal = Calendar.getInstance();

            Calendar dataAniversarioCal = Calendar.getInstance();
            dataAniversarioCal.setTime(nascimentoDependente);
            dataAniversarioCal.add(Calendar.YEAR, idadeLimite);

            if (dataAtualCal.after(dataAniversarioCal)) {
                salvaDependente = false;
            }
        }

        // Recuperando Usuario logado da Sessão
        HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
        Usuario usuarioOBJ = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

        // Setando Sócio na Sessão para ser utilizado por Dependente        
        long idSocioDependente = 0;

        if (usuarioOBJ.getCategoriaUsuario().equals(CategoriaUsuario.Sociedade)) {
            idSocioDependente = usuarioOBJ.getLogin();
        } else {
            if (!(req.getParameter("idSocio").isEmpty())) {
                idSocioDependente = Long.parseLong(req.getParameter("idSocio"));
            }
        }

        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(idSocioDependente);

        if (salvaDependente) {
            if (idDependente.equals("")) {
                Dependente dependenteOBJ = new Dependente();
                dependenteOBJ.setNomeDependente(nomeDependente);
                dependenteOBJ.setNascimentoDependente(nascimentoDependente);
                dependenteOBJ.setSexoDependente(sexoDependente);
                dependenteOBJ.setNacionalidadeDependente(nacionalidadeDependente);
                dependenteOBJ.setNaturalidadeDependente(naturalidadeDependente);
                dependenteOBJ.setEstadoCivilDependente(estadoCivilDependente);
                dependenteOBJ.setStatusDependente("d");
                dependenteOBJ.setParentescoDependente(parentescoOBJ);
                dependenteOBJ.setSocioDependente(socioOBJ);

                DependenteDAO dependenteDAO = new DependenteDAO();
                dependenteDAO.cadastrar(dependenteOBJ);

                req.setAttribute("msg", "Dependente " + dependenteOBJ.getNomeDependente() + " cadastrado com sucesso!");
            } else {
                Dependente dependenteOBJ = new Dependente();
                dependenteOBJ.setIdDependente(Long.parseLong(idDependente));
                dependenteOBJ.setNomeDependente(nomeDependente);
                dependenteOBJ.setNascimentoDependente(nascimentoDependente);
                dependenteOBJ.setSexoDependente(sexoDependente);
                dependenteOBJ.setNacionalidadeDependente(nacionalidadeDependente);
                dependenteOBJ.setNaturalidadeDependente(naturalidadeDependente);
                dependenteOBJ.setEstadoCivilDependente(estadoCivilDependente);
                dependenteOBJ.setParentescoDependente(parentescoOBJ);
                dependenteOBJ.setSocioDependente(socioOBJ);

                DependenteDAO dependenteDAO = new DependenteDAO();
                dependenteDAO.alterar(dependenteOBJ);

                req.setAttribute("msg", "Dependente " + dependenteOBJ.getNomeDependente() + " atualizado com sucesso!");
            }
        } else {
            req.setAttribute("msg", "Conforme o Estatuto: " + parentescoSt + ", homem, de " + idadeLimite + " anos não pode ser Dependente!");
        }

        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setSocioDependente(socioOBJ);

        DependenteDAO dependenteDAO = new DependenteDAO();
        ArrayList<Dependente> dependenteList = dependenteDAO.listarTodosPorSocio(dependenteOBJ);

        for (Dependente dependente : dependenteList) {
            Parentesco parentesco = new Parentesco();
            parentesco.setIdParentesco(dependente.getParentescoDependente().getIdParentesco());

            parentesco = parentescoDAO.consultarPorId(parentesco);

            dependente.setParentescoDependente(parentesco);
        }

        req.setAttribute("listaDependente", dependenteList);

        return pagina;
    }
}
