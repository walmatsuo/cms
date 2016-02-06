package br.cms.Controller.Sociedade.Dependente;

import br.cms.DAO.DependenteDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaUsuario;
import br.cms.Model.Dependente;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PesquisaDependente implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "sociedade/dependenteListar.jsp";

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

        sessaoUsuario.setAttribute("socioDependente", socioOBJ);

        String nomeDependente = req.getParameter("nomeDependente");

        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setSocioDependente(socioOBJ);
        dependenteOBJ.setNomeDependente(nomeDependente);

        DependenteDAO dependenteDAO = new DependenteDAO();
        ArrayList<Dependente> dependenteList = dependenteDAO.pesquisar(dependenteOBJ);

        req.setAttribute("listaDependente", dependenteList);
        req.setAttribute("msg", "Resultado: " + dependenteList.size() + " dependente(s)!");

        return pagina;
    }
}
