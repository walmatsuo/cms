package br.cms.Quartz;

import br.cms.DAO.EventoDAO;
import br.cms.DAO.EventoInscricaoDAO;
import br.cms.Model.Evento;
import br.cms.Model.EventoInscricao;
import java.util.ArrayList;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ConcluiEventoJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        Date dataAtual = new Date();

        EventoDAO eventoDAO = new EventoDAO();
        ArrayList<Evento> listaEvento = eventoDAO.listarTodos();

        for (Evento evento : listaEvento) {
            if (evento.getData().before(dataAtual)) {
                evento.setStatusEvento("c");

                eventoDAO.alterarStatus(evento);

                //Concluir inscrições do evento
                EventoInscricao eventoInscricao = new EventoInscricao();
                eventoInscricao.setEvento(evento);
                eventoInscricao.setStatusEventoInscricao("c");

                EventoInscricaoDAO eventoInscricaoDAO = new EventoInscricaoDAO();
                eventoInscricaoDAO.alterarStatusPorEvento(eventoInscricao);
            }
        }
    }

}
