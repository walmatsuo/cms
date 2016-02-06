package br.cms.Controller.Atendimento.Pdf;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import br.cms.Model.Socio;
import br.cms.Controller.Gerencia.Relatorio.GeradorRelatorio;
import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeraCarteirinhaDependente implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = null;

        String extensao = "pdf";
        String arquivoJrxml = "CarteirinhaDependente";
        String idDependente = req.getParameter("idDependente");

        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setIdDependente(Long.parseLong(idDependente));

        DependenteDAO DependenteDAO = new DependenteDAO();
        dependenteOBJ = DependenteDAO.consultarPorId(dependenteOBJ);

        if (dependenteOBJ != null) {
            Parentesco parentesco = new Parentesco();
            parentesco.setIdParentesco(dependenteOBJ.getParentescoDependente().getIdParentesco());

            ParentescoDAO parentescoDAO = new ParentescoDAO();
            parentesco = parentescoDAO.consultarPorId(parentesco);

            Socio socio = new Socio();
            socio.setIdSocio(dependenteOBJ.getSocioDependente().getIdSocio());

            SocioDAO socioDAO = new SocioDAO();
            socio = socioDAO.consultarPorId(socio);

            CategoriaSocio categoriaSocio = new CategoriaSocio();
            categoriaSocio.setIdCategoriaSocio(socio.getCategoriaSocio().getIdCategoriaSocio());

            CategoriaSocioDAO categoriaSocioDAO = new CategoriaSocioDAO();
            categoriaSocio = categoriaSocioDAO.consultarPorId(categoriaSocio);

            socio.setCategoriaSocio(categoriaSocio);

            dependenteOBJ.setParentescoDependente(parentesco);
            dependenteOBJ.setSocioDependente(socio);

            ArrayList<Dependente> dependenteList = new ArrayList<Dependente>();
            dependenteList.add(dependenteOBJ);

            JRDataSource jds = new JRBeanCollectionDataSource(dependenteList);

            GeradorRelatorio gr = new GeradorRelatorio();
            gr.gerarRelatorio(extensao, arquivoJrxml, res, jds);
        } else {
            pagina = "/principal.jsp";

            req.setAttribute("msg", "Não é possível gerar a carteirinha, Dependente inexistente !");
        }
        return pagina;
    }

}
