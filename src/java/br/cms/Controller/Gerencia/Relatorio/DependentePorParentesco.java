package br.cms.Controller.Gerencia.Relatorio;

import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import br.cms.Model.Socio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DependentePorParentesco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        String pagina = null;

        String extensao = req.getParameter("extensao");
        long idParentesco = Long.parseLong(req.getParameter("parentesco"));
        String arquivoJrxml = "DependentePorParentesco";

        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setStatusDependente("a");

        DependenteDAO dependenteDAO = new DependenteDAO();
        ArrayList<Dependente> dependenteList = dependenteDAO.listarTodosPorStatus(dependenteOBJ);
        ArrayList<Dependente> dependenteParentescoList = new ArrayList<Dependente>();

        for (Dependente dependente : dependenteList) {
            Parentesco parentesco = new Parentesco();
            parentesco.setIdParentesco(dependente.getParentescoDependente().getIdParentesco());

            Socio socio = new Socio();
            socio.setIdSocio(dependente.getSocioDependente().getIdSocio());

            ParentescoDAO parentescoDAO = new ParentescoDAO();
            parentesco = parentescoDAO.consultarPorId(parentesco);

            SocioDAO socioDAO = new SocioDAO();
            socio = socioDAO.consultarPorId(socio);

            // Setando Sócio e Parentesdo do Dependente
            dependente.setParentescoDependente(parentesco);
            dependente.setSocioDependente(socio);

            if (dependente.getParentescoDependente().getIdParentesco() == idParentesco) {
                dependenteParentescoList.add(dependente);
            }
        }

        if (dependenteParentescoList.size() > 0) {
            JRDataSource jds = new JRBeanCollectionDataSource(dependenteParentescoList);

            GeradorRelatorio gr = new GeradorRelatorio();
            gr.gerarRelatorio(extensao, arquivoJrxml, res, jds);
        } else {
            pagina = "GerenciaController?action=Relatorio.PreparaDependentePorParentesco";

            req.setAttribute("msg", "Não há dependentes vinculados com o parentesco selecionado!");
        }
        return pagina;
    }
}
