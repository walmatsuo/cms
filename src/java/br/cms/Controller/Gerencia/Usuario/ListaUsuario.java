package br.cms.Controller.Gerencia.Usuario;

import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaUsuario implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/usuarioListar.jsp";

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> usuarioList = usuarioDAO.listarTodos();

        req.setAttribute("listaUsuario", usuarioList);

        if (req.getAttribute("msg") == null) {
            req.setAttribute("msg", "Resultado: " + usuarioList.size() + " usuário(s)!");
        }

        return pagina;
    }
}
