package br.cms.Controller.Acesso;

import br.cms.Interface.Executa;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Saida implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "index.jsp";
        
        HttpSession sessaoUsuario = req.getSession();
        sessaoUsuario.invalidate();

        return pagina;
    }

}
