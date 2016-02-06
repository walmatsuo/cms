package br.cms.Controller.Gerencia.Usuario;

import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaUsuario;
import br.cms.Model.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveUsuario implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "GerenciaController?action=Usuario.ListaUsuario";

        long idUsuario = Long.parseLong(req.getParameter("idUsuario"));
        // Caso Categoria seja Gerencia, verificar abaixo se não é o Único ativo
        boolean removeUsuario = true;

        Usuario usuarioOBJ = new Usuario();
        usuarioOBJ.setIdUsuario(idUsuario);

        // Recuperando dados Usuário selecionado
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioOBJ = usuarioDAO.consultarPorId(usuarioOBJ);

        if (usuarioOBJ.getCategoriaUsuario().equals(CategoriaUsuario.Gerencia)) {
            // Recuperando quantidade Usuários Gerencia Ativos
            int qtdeGerenteAtivo = usuarioDAO.retornarQuantidadePorCategoria(usuarioOBJ);

            // Verificando se existe pelo menos UM Usuário Gerencia Ativo
            if (qtdeGerenteAtivo == 1) {
                removeUsuario = false;
            }
        }

        if (removeUsuario) {
            usuarioOBJ.setIdUsuario(idUsuario);
            usuarioOBJ.setStatusUsuario("x");

            usuarioDAO.alterarStatus(usuarioOBJ);

            req.setAttribute("msg", "Usuário excluído com sucesso!");
        } else {
            req.setAttribute("msg", "Usuário não pode ser excluído, pois é o único gerente do sistema!");
        }
        return pagina;
    }
}
