package br.cms.Controller.Sociedade.Dependente;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import br.cms.Model.Socio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CadastraDependente implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = null;

        // Verificando se existe Parentescos cadastrados para prosseguir
        Parentesco parentescoOBJ = new Parentesco();
        parentescoOBJ.setStatusParentesco("a");

        ParentescoDAO parentescoDAO = new ParentescoDAO();
        int qtdeParentesco = parentescoDAO.retornarQuantidadePorStatus(parentescoOBJ);

        if (qtdeParentesco > 0) {

            HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
            Socio socioOBJ = (Socio) sessaoUsuario.getAttribute("socioDependente");

            CategoriaSocioDAO categoriaSocioDAO = new CategoriaSocioDAO();
            CategoriaSocio categoriaSocioOBJ = categoriaSocioDAO.consultarPorId(socioOBJ.getCategoriaSocio());

            int lotacaoMaxima = categoriaSocioOBJ.getLotacaoMaxima();

            Dependente dependenteOBJ = new Dependente();
            dependenteOBJ.setSocioDependente(socioOBJ);

            DependenteDAO dependenteDAO = new DependenteDAO();
            int qtdeDependentes = dependenteDAO.retornarQuantidadePorSocio(dependenteOBJ);

            if (lotacaoMaxima > qtdeDependentes) {
                ArrayList<Parentesco> parentescoList = parentescoDAO.listarTodos();

                req.setAttribute("listaParentesco", parentescoList);

                pagina = "sociedade/dependenteForm.jsp";
            } else {
                ArrayList<Dependente> dependenteList = dependenteDAO.listarTodosPorSocio(dependenteOBJ);

                for (Dependente dependente : dependenteList) {
                    Parentesco parentesco = new Parentesco();
                    parentesco.setIdParentesco(dependente.getParentescoDependente().getIdParentesco());

                    parentesco = parentescoDAO.consultarPorId(parentesco);

                    dependente.setParentescoDependente(parentesco);
                }

                req.setAttribute("listaDependente", dependenteList);
                req.setAttribute("msg", "Limite de dependentes ativos esgotado (Máximo: " + lotacaoMaxima + ")!");

                pagina = "sociedade/dependenteListar.jsp";
            }
        } else {
            req.setAttribute("msg", "Parentesco: Solicite à Gerencia, o cadastro dos parentescos.");
            pagina = "principal.jsp";
        }
        return pagina;
    }
}
