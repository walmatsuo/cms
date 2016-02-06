package br.cms.Controller.Gerencia.Relatorio;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import br.cms.Model.Socio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SocioPorCategoria implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        String pagina = null;

        String extensao = req.getParameter("extensao");
        long idCategoriaSocio = Long.parseLong(req.getParameter("categoriaSocio"));
        String arquivoJrxml = "SocioPorCategoria";

        SocioDAO socioDAO = new SocioDAO();
        ArrayList<Socio> socioList = socioDAO.listarTodos();
        ArrayList<Socio> socioCategoriaList = new ArrayList<Socio>();

        for (Socio socio : socioList) {
            CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
            categoriaSocioOBJ.setIdCategoriaSocio(socio.getCategoriaSocio().getIdCategoriaSocio());

            CategoriaSocioDAO categoriaSocioDAO = new CategoriaSocioDAO();
            categoriaSocioOBJ = categoriaSocioDAO.consultarPorId(categoriaSocioOBJ);

            socio.setCategoriaSocio(categoriaSocioOBJ);

            if (socio.getCategoriaSocio().getIdCategoriaSocio() == idCategoriaSocio) {
                socioCategoriaList.add(socio);
            }
        }

        if (socioCategoriaList.size() > 0) {
            JRDataSource jds = new JRBeanCollectionDataSource(socioCategoriaList);

            GeradorRelatorio gr = new GeradorRelatorio();
            gr.gerarRelatorio(extensao, arquivoJrxml, res, jds);
        }else{
             pagina = "gerencia/relatorioSocioPorCategoria.jsp";
             
             req.setAttribute("msg", "Não há Sócios cadastrados na Categoria selecionada!");
        }
        return pagina;
    }

}
