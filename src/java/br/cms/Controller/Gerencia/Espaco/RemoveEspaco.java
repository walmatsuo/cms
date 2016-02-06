package br.cms.Controller.Gerencia.Espaco;

import br.cms.DAO.EspacoDAO;
import br.cms.DAO.ReservaDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Espaco;
import br.cms.Model.Reserva;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveEspaco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "GerenciaController?action=Espaco.ListaEspaco";

        long idEspaco = Long.parseLong(req.getParameter("idEspaco"));

        Espaco espacoOBJ = new Espaco();
        espacoOBJ.setIdEspaco(idEspaco);
        espacoOBJ.setStatusEspaco("x");

        Reserva reservaOBJ = new Reserva();
        reservaOBJ.setEspacoReserva(espacoOBJ);
        reservaOBJ.setStatusReserva("a");

        ReservaDAO reservaDAO = new ReservaDAO();
        int reservaPorEspaco = reservaDAO.retornarQuantidadePorEspaco(reservaOBJ);

        if (reservaPorEspaco == 0) {
            EspacoDAO espacoDAO = new EspacoDAO();
            espacoDAO.alterarStatus(espacoOBJ);

            req.setAttribute("msg", "Espaço excluído com sucesso!");
        } else {
            req.setAttribute("msg", "Espaço não pode ser excluído, pois possui " + reservaPorEspaco + " reserva(s) vinculada(s) a ele!");
        }

        return pagina;
    }
}
