
package br.cms.Controller.Sociedade.Dependente;

import br.cms.DAO.DependenteDAO;
import br.cms.DAO.ParentescoDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaUsuario;
import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListaDependente implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/dependenteListar.jsp";

        // Verificando se existe Parentescos cadastrados para prosseguir
        Parentesco parentescoOBJ = new Parentesco();
        parentescoOBJ.setStatusParentesco("a");

        ParentescoDAO parentescoDAO = new ParentescoDAO();
        int qtdeParentesco = parentescoDAO.retornarQuantidadePorStatus(parentescoOBJ);

        if (qtdeParentesco > 0) {
            // Recuperando Usuario logado da Sessão
            HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
            Usuario usuarioOBJ = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

            // Setando Sócio na Sessão para ser utilizado por Dependente
            long idSocioDependente = 0;

            if (usuarioOBJ.getCategoriaUsuario().equals(CategoriaUsuario.Sociedade)) {
                idSocioDependente = usuarioOBJ.getLogin();
            } else {
                if (!(req.getParameter("idSocio").isEmpty())) {
                    idSocioDependente = Long.parseLong(req.getParameter("idSocio"));
                }
            }

            Socio socioOBJ = new Socio();
            socioOBJ.setIdSocio(idSocioDependente);

            SocioDAO socioDAO = new SocioDAO();
            socioOBJ = socioDAO.consultarPorId(socioOBJ);

            // Setando objeto socio na Sessão para ser utilizado em outras funcionalidades de Dependente (Cadastrar, Bloquear e Validar)
            sessaoUsuario.setAttribute("socioDependente", socioOBJ);

            Dependente dependenteOBJ = new Dependente();
            dependenteOBJ.setSocioDependente(socioOBJ);

            DependenteDAO dependenteDAO = new DependenteDAO();
            ArrayList<Dependente> dependenteList = dependenteDAO.listarTodosPorSocio(dependenteOBJ);

            for (Dependente dependente : dependenteList) {
                Parentesco parentesco = new Parentesco();
                parentesco.setIdParentesco(dependente.getParentescoDependente().getIdParentesco());

                parentesco = parentescoDAO.consultarPorId(parentesco);

                dependente.setParentescoDependente(parentesco);
            }

            req.setAttribute("listaDependente", dependenteList);
            req.setAttribute("msg", "Resultado: " + dependenteList.size() + " dependente(s)!");
        }else{
            req.setAttribute("msg", "Parentesco: Solicite à Gerencia, o cadastro dos parentescos.");
            pagina = "principal.jsp";
        }

        return pagina;
    }
}
