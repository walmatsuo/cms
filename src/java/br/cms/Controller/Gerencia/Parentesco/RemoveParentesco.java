package br.cms.Controller.Gerencia.Parentesco;

import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveParentesco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "GerenciaController?action=Parentesco.ListaParentesco";

        int idParentesco = Integer.parseInt(req.getParameter("idParentesco"));

        Parentesco parentescoOBJ = new Parentesco();
        parentescoOBJ.setIdParentesco(idParentesco);
        parentescoOBJ.setStatusParentesco("x");

        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setParentescoDependente(parentescoOBJ);

        DependenteDAO dependenteDAO = new DependenteDAO();
        int dependentePorParentesco = dependenteDAO.retornarQuantidadePorParentesco(dependenteOBJ);

        if (dependentePorParentesco == 0) {
            ParentescoDAO parentescoDAO = new ParentescoDAO();
            parentescoDAO.alterarStatus(parentescoOBJ);

            req.setAttribute("msg", "Parentesco excluído com sucesso!");
        } else {
            req.setAttribute("msg", "Parentesco não pode ser excluído, pois possui " + dependentePorParentesco + " dependentes(s) vinculado(s) à ele!");
        }

        return pagina;
    }
}
