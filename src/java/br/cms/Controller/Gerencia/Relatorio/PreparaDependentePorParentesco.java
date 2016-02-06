package br.cms.Controller.Gerencia.Relatorio;

import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Parentesco;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreparaDependentePorParentesco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/relatorioDependentePorParentesco.jsp";

        // Verificando se existe Parentescos cadastrados para prosseguir
        Parentesco parentescoOBJ = new Parentesco();
        parentescoOBJ.setStatusParentesco("a");

        ParentescoDAO parentescoDAO = new ParentescoDAO();
        int qtdeParentesco = parentescoDAO.retornarQuantidadePorStatus(parentescoOBJ);

        if (qtdeParentesco > 0) {
            ArrayList<Parentesco> parentescoList = parentescoDAO.listarTodos();

            req.setAttribute("listaParentesco", parentescoList);
        } else {
            req.setAttribute("msg", "Parentesco: Solicite à Gerencia, o cadastro dos parentescos.");
            pagina = "principal.jsp";
        }
        
        return pagina;
    }
}
