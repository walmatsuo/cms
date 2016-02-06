package br.cms.Controller.Sociedade.Reserva;

import br.cms.DAO.EspacoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Espaco;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FiltraReserva implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/calendario.jsp";

        String idEspaco = req.getParameter("espaco");

        // Recuperando Espaço selecionado pela Gerencia/Atendimento e salvando na sessão para carregar os eventos dess Espaço
        Espaco espacoOBJ = new Espaco();
        espacoOBJ.setIdEspaco(Long.parseLong(idEspaco));

        EspacoDAO espacoDAO = new EspacoDAO();
        espacoOBJ = espacoDAO.consultarPorId(espacoOBJ);

        // Setando Espaço selecionado na Sessão
        HttpSession sessaoUsuario = req.getSession();
        sessaoUsuario.setAttribute("espacoReserva", espacoOBJ);

        return pagina;
    }
}
