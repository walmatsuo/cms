package br.cms.Controller.Sociedade.Reserva;

import br.cms.DAO.EspacoDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaUsuario;
import br.cms.Model.Espaco;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RecuperaReserva implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "principal.jsp";

        // Buscando Quantidade de Espaços Ativos
        Espaco espacoOBJ = new Espaco();
        espacoOBJ.setStatusEspaco("a");

        EspacoDAO espacoDAO = new EspacoDAO();
        int qtdeEspaco = espacoDAO.retornarQuantidadePorStatus(espacoOBJ);

        // Buscando Quantidade de Sócios Ativos
        Socio socioOBJ = new Socio();
        socioOBJ.setStatusSocio("a");

        SocioDAO socioDAO = new SocioDAO();
        int qtdeSocio = socioDAO.retornarQuantidadePorStatus(socioOBJ);

        // Se existir Espaço e Sócio ativos, prossegue
        if ((qtdeEspaco > 0) && (qtdeSocio > 0)) {
            // Recuperando Usuario logado da Sessão
            HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
            Usuario usuarioOBJ = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

            if (usuarioOBJ.getCategoriaUsuario().equals(CategoriaUsuario.Sociedade)) {
                pagina = "sociedade/calendario.jsp";
            } else {
                ArrayList<Espaco> espacoList = espacoDAO.listarTodos();

                req.setAttribute("listaEspaco", espacoList);

                pagina = "atendimento/calendarioHome.jsp";
            }
            // Caso contrário, retorna o erro
        } else if ((qtdeEspaco == 0) && (qtdeSocio > 0)) {
            req.setAttribute("msg", "* Espaço: Solicite à Gerencia, o cadastro dos espaços!");
        } else if ((qtdeEspaco > 0) && (qtdeSocio == 0)) {
            req.setAttribute("msg", "* Sócio: Cadastre e valide os sócios!");
        } else {
            req.setAttribute("msg", "* Espaço: Solicite à Gerencia, o cadastro dos espaços! |  * Sócio: Cadastre e valide os sócios!");
        }

        return pagina;
    }
}
