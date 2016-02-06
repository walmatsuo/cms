package br.cms.Controller.Gerencia.Usuario;

import br.cms.Interface.Executa;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastraUsuario implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/usuarioForm.jsp";

        return pagina;
    }
}