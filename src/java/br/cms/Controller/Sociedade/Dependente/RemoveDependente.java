package br.cms.Controller.Sociedade.Dependente;

import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveDependente implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/dependenteListar.jsp";

        long idDependente = Long.parseLong(req.getParameter("idDependente"));

        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setIdDependente(idDependente);

        DependenteDAO dependenteDAO = new DependenteDAO();
        dependenteOBJ = dependenteDAO.consultarPorId(dependenteOBJ);
        dependenteOBJ.setStatusDependente("x");
        dependenteDAO.alterarStatus(dependenteOBJ);

        ArrayList<Dependente> dependenteList = dependenteDAO.listarTodosPorSocio(dependenteOBJ);

        for (Dependente dependente : dependenteList) {
            Parentesco parentescoOBJ = new Parentesco();
            parentescoOBJ.setIdParentesco(dependente.getParentescoDependente().getIdParentesco());
            
            ParentescoDAO parentescoDAO = new ParentescoDAO();
            parentescoOBJ = parentescoDAO.consultarPorId(parentescoOBJ);
            
            dependente.setParentescoDependente(parentescoOBJ);
        }
        
        req.setAttribute("listaDependente", dependenteList);
        req.setAttribute("msg", "Dependente excluído com sucesso!");

        return pagina;
    }
}
