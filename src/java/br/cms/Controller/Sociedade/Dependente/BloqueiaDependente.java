package br.cms.Controller.Sociedade.Dependente;

import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BloqueiaDependente implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/dependenteListar.jsp";

        long idDependente = Long.parseLong(req.getParameter("idDependente"));

        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setIdDependente(idDependente);
        dependenteOBJ.setStatusDependente("b");

        DependenteDAO dependenteDAO = new DependenteDAO();
        dependenteDAO.alterarStatus(dependenteOBJ);
       
        dependenteOBJ = dependenteDAO.consultarPorId(dependenteOBJ);

        ArrayList<Dependente> dependenteList = dependenteDAO.listarTodosPorSocio(dependenteOBJ);

        for (Dependente dependente : dependenteList) {
            Parentesco parentesco = new Parentesco();
            parentesco.setIdParentesco(dependente.getParentescoDependente().getIdParentesco());

            ParentescoDAO parentescoDAO = new ParentescoDAO();
            parentesco = parentescoDAO.consultarPorId(parentesco);

            dependente.setParentescoDependente(parentesco);
        }
        
        req.setAttribute("listaDependente", dependenteList);
        req.setAttribute("msg", "Dependente " + dependenteOBJ.getNomeDependente() + " bloqueado(a)!");

        return pagina;
    }
}
