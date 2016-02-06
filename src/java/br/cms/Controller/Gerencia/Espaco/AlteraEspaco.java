package br.cms.Controller.Gerencia.Espaco;

import br.cms.DAO.EspacoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Espaco;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlteraEspaco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/espacoForm.jsp";
        
        long idEspaco = Long.parseLong(req.getParameter("idEspaco"));

        Espaco espacoOBJ = new Espaco();
        espacoOBJ.setIdEspaco(idEspaco);

        EspacoDAO espacoDAO = new EspacoDAO();
        espacoOBJ = espacoDAO.consultarPorId(espacoOBJ);

        req.setAttribute("espaco", espacoOBJ);

        return pagina;
    }
}
