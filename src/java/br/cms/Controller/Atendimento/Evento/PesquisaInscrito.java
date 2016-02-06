package br.cms.Controller.Atendimento.Evento;

import br.cms.DAO.EventoInscricaoDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Evento;
import br.cms.Model.EventoInscricao;
import br.cms.Model.Socio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PesquisaInscrito implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "atendimento/inscritoListar.jsp";

        String nomeSocio = req.getParameter("nomeInscrito");
        long idEvento = Long.parseLong(req.getParameter("idEvento"));

        Socio socioOBJ = new Socio();
        socioOBJ.setNomeSocio(nomeSocio);

        SocioDAO socioDAO = new SocioDAO();
        ArrayList<Socio> socioList = socioDAO.pesquisar(socioOBJ);

        Evento eventoOBJ = new Evento();
        eventoOBJ.setIdEvento(idEvento);

        EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
        eventoInscricaoOBJ.setEvento(eventoOBJ);

        EventoInscricaoDAO eventoInscricaoDAO = new EventoInscricaoDAO();
        ArrayList<EventoInscricao> eventoInscricaoList = eventoInscricaoDAO.listarTodosInscritos(eventoInscricaoOBJ);
        ArrayList<EventoInscricao> eventoInscricaoPesqList = new ArrayList<EventoInscricao>();

        for (Socio socio : socioList) {
            for (EventoInscricao eventoInscricao : eventoInscricaoList) {
                if (socio.getIdSocio()== eventoInscricao.getSocio().getIdSocio()) {
                    eventoInscricao.setSocio(socio);
                    eventoInscricaoPesqList.add(eventoInscricao);

                }
            }
        }

        req.setAttribute("listaInscrito", eventoInscricaoPesqList);
        req.setAttribute("eventoInscrito", eventoOBJ);
        req.setAttribute("msg", "Resultado: " + eventoInscricaoPesqList.size() + " incrito(s)!");

        return pagina;
    }
}
