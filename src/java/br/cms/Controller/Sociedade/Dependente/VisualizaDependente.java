package br.cms.Controller.Sociedade.Dependente;

import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisualizaDependente implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/dependenteVisualizaForm.jsp";

        // Verificando se existe Parentescos cadastrados para prosseguir
        Parentesco parentescoOBJ = new Parentesco();
        parentescoOBJ.setStatusParentesco("a");

        ParentescoDAO parentescoDAO = new ParentescoDAO();
        int qtdeParentesco = parentescoDAO.retornarQuantidadePorStatus(parentescoOBJ);

        if (qtdeParentesco > 0) {

            long idDependente = Long.parseLong(req.getParameter("idDependente"));

            Dependente dependenteOBJ = new Dependente();
            dependenteOBJ.setIdDependente(idDependente);

            DependenteDAO dependenteDAO = new DependenteDAO();
            dependenteOBJ = dependenteDAO.consultarPorId(dependenteOBJ);

            parentescoOBJ.setIdParentesco(dependenteOBJ.getParentescoDependente().getIdParentesco());

            parentescoOBJ = parentescoDAO.consultarPorId(parentescoOBJ);

            dependenteOBJ.setParentescoDependente(parentescoOBJ);

            ArrayList<Parentesco> parentescoList = parentescoDAO.listarTodos();

            req.setAttribute("listaParentesco", parentescoList);
            req.setAttribute("dependente", dependenteOBJ);

        } else {
            req.setAttribute("msg", "Parentesco: Solicite à Gerencia, o cadastro dos parentescos.");
            pagina = "principal.jsp";
        }

        return pagina;
    }
    
}
