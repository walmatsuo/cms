package br.cms.Controller.Gerencia.Parentesco;

import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Parentesco;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlteraParentesco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/parentescoForm.jsp";

        int idParentesco = Integer.parseInt(req.getParameter("idParentesco"));

        Parentesco parentescoOBJ = new Parentesco();
        parentescoOBJ.setIdParentesco(idParentesco);

        ParentescoDAO parentescoDAO = new ParentescoDAO();
        parentescoOBJ = parentescoDAO.consultarPorId(parentescoOBJ);

        req.setAttribute("parentesco", parentescoOBJ);

        return pagina;
    }
}
