package br.cms.Controller.Acesso;

import br.cms.Interface.Executa;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecuperaSenha implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "recuperarSenha.jsp";

        return pagina;
    }
}
