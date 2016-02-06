package br.cms.Controller.Gerencia.Relatorio;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import br.cms.Model.Socio;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SocioPorMes implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        String pagina = null;

        String extensao = req.getParameter("extensao");
        int mes = Integer.parseInt(req.getParameter("mes"));
        int ano = Integer.parseInt(req.getParameter("ano"));
        String arquivoJrxml = "SocioPorMes";

        SocioDAO socioDAO = new SocioDAO();
        ArrayList<Socio> listaSocio = socioDAO.listarTodos();
        ArrayList<Socio> listaSocioMes = new ArrayList<Socio>();

        for (Socio socio : listaSocio) {
            CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
            categoriaSocioOBJ.setIdCategoriaSocio(socio.getCategoriaSocio().getIdCategoriaSocio());

            CategoriaSocioDAO categoriaSocioDAO = new CategoriaSocioDAO();
            categoriaSocioOBJ = categoriaSocioDAO.consultarPorId(categoriaSocioOBJ);

            socio.setCategoriaSocio(categoriaSocioOBJ);

            Calendar inclusaoSocioCal = Calendar.getInstance();
            inclusaoSocioCal.setTime(socio.getInclusaoSocio());

            if ((inclusaoSocioCal.get(Calendar.MONTH) == mes) && (inclusaoSocioCal.get(Calendar.YEAR) == ano)) {
                listaSocioMes.add(socio);
            }
        }

        if (listaSocioMes.size() > 0) {
            JRDataSource jds = new JRBeanCollectionDataSource(listaSocioMes);

            GeradorRelatorio gr = new GeradorRelatorio();
            gr.gerarRelatorio(extensao, arquivoJrxml, res, jds);
        } else {
            pagina = "gerencia/relatorioSocioPorMes.jsp";
            
            req.setAttribute("msg", "Não há Sócios cadastrados para o mês: " + mes + " / " + ano + "!");
        }

        return pagina;
    }

}
