package br.cms.Controller.Sociedade.Reserva;

import br.cms.DAO.ReservaDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Reserva;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelaReserva implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String idReserva = req.getParameter("id");

        Reserva reservaOBJ = new Reserva();
        reservaOBJ.setId(Long.parseLong(idReserva));
        reservaOBJ.setStatusReserva("k");

        ReservaDAO reservaDAO = new ReservaDAO();
        reservaDAO.alterarStatus(reservaOBJ);
        
        req.setAttribute("msg", "Reserva cancelada!");

        return null;
    }
}
