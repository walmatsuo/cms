package br.cms.Controller.Atendimento.Evento;

import br.cms.Interface.Executa;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastraEvento implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "atendimento/eventoForm.jsp";

        return pagina;
    }

}
