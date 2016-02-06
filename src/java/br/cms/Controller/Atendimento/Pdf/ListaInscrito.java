package br.cms.Controller.Atendimento.Pdf;

import br.cms.Controller.Gerencia.Relatorio.GeradorRelatorio;
import br.cms.DAO.EventoDAO;
import br.cms.DAO.EventoInscricaoDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Evento;
import br.cms.Model.EventoInscricao;
import br.cms.Model.Socio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ListaInscrito implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = null;

        String extensao = "pdf";
        long idEvento = Long.parseLong(req.getParameter("idEvento"));
        String arquivoJrxml = "ListaInscrito";
        
        Evento eventoOBJ = new Evento();
        eventoOBJ.setIdEvento(idEvento);

        EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
        eventoInscricaoOBJ.setEvento(eventoOBJ);

        EventoInscricaoDAO eventoInscricaoDAO = new EventoInscricaoDAO();
        ArrayList<EventoInscricao> eventoInscricaoList = eventoInscricaoDAO.listarTodosInscritos(eventoInscricaoOBJ);

        if (eventoInscricaoList.size() > 0) {
            for (EventoInscricao eventoInscricao : eventoInscricaoList) {
                Socio socio = eventoInscricao.getSocio();

                SocioDAO socioDAO = new SocioDAO();
                socio = socioDAO.consultarPorId(socio);

                EventoDAO eventoDAO = new EventoDAO();
                eventoOBJ = eventoDAO.consultarPorId(eventoOBJ);

                eventoInscricao.setSocio(socio);
                eventoInscricao.setEvento(eventoOBJ);
            }

            JRDataSource jds = new JRBeanCollectionDataSource(eventoInscricaoList);

            GeradorRelatorio gr = new GeradorRelatorio();
            gr.gerarRelatorio(extensao, arquivoJrxml, res, jds);
        }

        return pagina;
    }
}
