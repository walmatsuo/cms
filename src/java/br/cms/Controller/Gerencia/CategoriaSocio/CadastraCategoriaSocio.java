package br.cms.Controller.Gerencia.CategoriaSocio;

import br.cms.Interface.Executa;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastraCategoriaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/categoriaForm.jsp";

        return pagina;
    }
}