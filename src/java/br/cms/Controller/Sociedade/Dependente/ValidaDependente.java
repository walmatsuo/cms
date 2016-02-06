package br.cms.Controller.Sociedade.Dependente;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import br.cms.Model.Socio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidaDependente implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/dependenteListar.jsp";

        // Recuperando Usuario logado da Sessão
        HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
        Socio socioOBJ = (Socio) sessaoUsuario.getAttribute("socioDependente");
        
        CategoriaSocioDAO categoriaSocioDAO = new CategoriaSocioDAO();
        CategoriaSocio categoriaSocioOBJ = categoriaSocioDAO.consultarPorId(socioOBJ.getCategoriaSocio());

        int lotacaoMaxima = categoriaSocioOBJ.getLotacaoMaxima();

        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setSocioDependente(socioOBJ);

        DependenteDAO dependenteDAO = new DependenteDAO();
        int qtdeDependentes = dependenteDAO.retornarQuantidadePorSocio(dependenteOBJ);

        if (lotacaoMaxima > qtdeDependentes) {
            long idDependente = Long.parseLong(req.getParameter("idDependente"));

            dependenteOBJ.setIdDependente(idDependente);
            dependenteOBJ.setStatusDependente("a");
            dependenteDAO.alterarStatus(dependenteOBJ);

            dependenteOBJ = dependenteDAO.consultarPorId(dependenteOBJ);

            req.setAttribute("msg", "Dependente " + dependenteOBJ.getNomeDependente() + " ativado(a)!");
        } else {
            req.setAttribute("msg", "Limite de dependentes ativos esgotado (Máximo: " + lotacaoMaxima + ")!");
        }

        ArrayList<Dependente> dependenteList = dependenteDAO.listarTodosPorSocio(dependenteOBJ);

        for (Dependente dependente : dependenteList) {
            Parentesco parentescoOBJ = new Parentesco();
            parentescoOBJ.setIdParentesco(dependente.getParentescoDependente().getIdParentesco());

            ParentescoDAO parentescoDAO = new ParentescoDAO();
            parentescoOBJ = parentescoDAO.consultarPorId(parentescoOBJ);

            dependente.setParentescoDependente(parentescoOBJ);
        }

        req.setAttribute("listaDependente", dependenteList);

        return pagina;
    }

}
