package br.cms.Controller.Gerencia.Parentesco;

import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Parentesco;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PesquisaParentesco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/parentescoListar.jsp";
        
        String nomeParentesco = req.getParameter("nomeParentesco");

        Parentesco parentescoOBJ = new Parentesco();
        parentescoOBJ.setNomeParentesco(nomeParentesco);

        ParentescoDAO parentescoDAO = new ParentescoDAO();
        ArrayList<Parentesco> parentescoList = parentescoDAO.pesquisar(parentescoOBJ);

        req.setAttribute("listaParentesco", parentescoList);
        req.setAttribute("msg", "Resultado: " + parentescoList.size() + " parentesco(s)!");

        return pagina;
    }
}
