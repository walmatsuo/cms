package br.cms.Quartz;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class GerenciadorQuartz extends QuartzInitializerListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        super.contextInitialized(sce);

        ServletContext ctx = sce.getServletContext();

        StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);

        try {
            Scheduler scheduler = factory.getScheduler();

            JobKey jkConcluirReserva = new JobKey("ConcluiReserva", "ReservaJobs");
            JobDetail jobConcluirReserva = JobBuilder.newJob(ConcluiReservaJob.class).
                    withIdentity(jkConcluirReserva).
                    build();

            JobKey jkConcluirEvento = new JobKey("ConcluiEvento", "EventoJobs");
            JobDetail jobConcluirEvento = JobBuilder.newJob(ConcluiEventoJob.class).
                    withIdentity(jkConcluirEvento).
                    build();

            JobKey jkNotificaFilho18Anos = new JobKey("NotificaFilho18Anos", "DependenteJobs");
            JobDetail jobNotificarFilho18Anos = JobBuilder.newJob(NotificaFilho18AnosJob.class).
                    withIdentity(jkNotificaFilho18Anos).
                    build();

            JobKey jkBloqueiaFilho18Anos = new JobKey("BloqueiaFilho18Anos", "DependenteJobs");
            JobDetail jobBloquearFilho18Anos = JobBuilder.newJob(BloqueiaFilho18AnosJob.class).
                    withIdentity(jkBloqueiaFilho18Anos).
                    build();

            Trigger tgrMeiaHora = TriggerBuilder
                    .newTrigger()
                    .withIdentity("TodaMeiaHora", "Horas")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 * * * ?")) // A CADA 30 MINUTOS
                    .startNow()
                    .build();

            Trigger tgrUmMinuto = TriggerBuilder
                    .newTrigger()
                    .withIdentity("TodoUmMinuto", "Horas")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")) // A CADA 1 MINUTO
                    .startNow()
                    .build();

            Trigger trgTodoDiaHora08 = TriggerBuilder
                    .newTrigger()
                    .withIdentity("QuartaDomingoAs08", "Semanal")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 8 ? * WED,SUN")) // Toda Quarta e Domingo, AS 08:00:00
                    .startNow()
                    .build();

            Trigger trgTodoDiaHora04 = TriggerBuilder
                    .newTrigger()
                    .withIdentity("TodoDiaHora04", "Horas")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 4 * * ?")) // TODO DIA, AS 04:00:00
                    .startNow()
                    .build();

            scheduler.scheduleJob(jobConcluirReserva, tgrMeiaHora);
            scheduler.scheduleJob(jobConcluirEvento, tgrUmMinuto);
            scheduler.scheduleJob(jobNotificarFilho18Anos, trgTodoDiaHora08);
            scheduler.scheduleJob(jobBloquearFilho18Anos, trgTodoDiaHora04);

            scheduler.start();
        } catch (SchedulerException e) {
            ctx.log("Erro ao disparar gatilhos", e);
        }
    }

}
