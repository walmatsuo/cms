package br.cms.Controller.Atendimento.Pdf;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import br.cms.Model.Socio;
import br.cms.Controller.Gerencia.Relatorio.GeradorRelatorio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeraCarteirinhaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        
        String pagina = null;

        String extensao = "pdf";
        String arquivoJrxml = "CarteirinhaSocio";
        String idSocio = req.getParameter("idSocio");

        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(Long.parseLong(idSocio));

        SocioDAO socioDAO = new SocioDAO();
        socioOBJ = socioDAO.consultarPorId(socioOBJ);

        if (socioOBJ != null) {
            CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
            categoriaSocioOBJ.setIdCategoriaSocio(socioOBJ.getCategoriaSocio().getIdCategoriaSocio());

            CategoriaSocioDAO categoriaSocioDAO = new CategoriaSocioDAO();
            categoriaSocioOBJ = categoriaSocioDAO.consultarPorId(categoriaSocioOBJ);

            socioOBJ.setCategoriaSocio(categoriaSocioOBJ);

            ArrayList<Socio> socioList = new ArrayList<Socio>();
            socioList.add(socioOBJ);

            JRDataSource jds = new JRBeanCollectionDataSource(socioList);

            GeradorRelatorio gr = new GeradorRelatorio();
            gr.gerarRelatorio(extensao, arquivoJrxml, res, jds);
        } else {
            pagina = "/principal.jsp";
            
            req.setAttribute("msg", "Não é possível gerar a carteirinha, Sócio(a) inexistente !");
        }
        return pagina;
    }

}
