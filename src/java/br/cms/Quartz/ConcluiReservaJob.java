package br.cms.Quartz;

import br.cms.DAO.ReservaDAO;
import br.cms.Model.Reserva;
import java.util.ArrayList;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ConcluiReservaJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        Date dataAtual = new Date();

        Reserva reservaOBJ = new Reserva();
        reservaOBJ.setStatusReserva("a");

        ReservaDAO reservaDAO = new ReservaDAO();
        ArrayList<Reserva> listaReserva = reservaDAO.listarPorStatus(reservaOBJ);

        for (Reserva reserva : listaReserva) {
             if (reserva.getEnd().before(dataAtual)) {
                reserva.setStatusReserva("c");

                reservaDAO.alterarStatus(reserva);
            }
        }

    }

}
