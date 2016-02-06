package br.cms.Controller.Gerencia.Parentesco;

import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Parentesco;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalvaParentesco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "GerenciaController?action=Parentesco.ListaParentesco";

        String idParentesco = req.getParameter("idParentesco");
        int grauParentesco = Integer.parseInt(req.getParameter("grauParentesco"));
        String nomeParentesco = req.getParameter("nomeParentesco");

        if (idParentesco.equals("")) {
            Parentesco parentescoOBJ = new Parentesco();
            parentescoOBJ.setGrauParentesco(grauParentesco);
            parentescoOBJ.setNomeParentesco(nomeParentesco);
            parentescoOBJ.setStatusParentesco("a");

            ParentescoDAO parentescoDAO = new ParentescoDAO();
            parentescoDAO.cadastrar(parentescoOBJ);

            req.setAttribute("msg", "Parentesco " + parentescoOBJ.getNomeParentesco() + " cadastrado com sucesso!");
        } else {
            Parentesco parentescoOBJ = new Parentesco();
            parentescoOBJ.setIdParentesco(Integer.parseInt(idParentesco));
            parentescoOBJ.setGrauParentesco(grauParentesco);
            parentescoOBJ.setNomeParentesco(nomeParentesco);

            ParentescoDAO parentescoDAO = new ParentescoDAO();
            parentescoDAO.alterar(parentescoOBJ);

            req.setAttribute("msg", "Parentesco " + parentescoOBJ.getNomeParentesco() + " atualizado com sucesso!");
        }

        return pagina;
    }
}
