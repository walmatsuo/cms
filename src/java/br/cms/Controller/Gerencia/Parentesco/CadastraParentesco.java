package br.cms.Controller.Gerencia.Parentesco;

import br.cms.Interface.Executa;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastraParentesco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/parentescoForm.jsp";

        return pagina;
    }
}
