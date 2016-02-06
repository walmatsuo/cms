package br.cms.Controller.Gerencia.Usuario;

import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PesquisaUsuario implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "gerencia/usuarioListar.jsp";

        String nomeUsuario = req.getParameter("nomeUsuario");

        Usuario usuarioOBJ = new Usuario();
        usuarioOBJ.setNomeUsuario(nomeUsuario);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> usuarioList = usuarioDAO.pesquisar(usuarioOBJ);

        req.setAttribute("listaUsuario", usuarioList);
        
        req.setAttribute("msg", "Resultado: " + usuarioList.size() + " usuário(s)!");

        return pagina;
    }
}
