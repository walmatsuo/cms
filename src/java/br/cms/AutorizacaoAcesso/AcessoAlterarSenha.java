package br.cms.AutorizacaoAcesso;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import br.cms.Model.Usuario;

public class AcessoAlterarSenha implements Filter {

    private Usuario usuario;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpSession sessaoUsuario = ((HttpServletRequest) request).getSession();

        usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

        if (usuario != null) {
            if (usuario.getStatusUsuario().equals("p")) {
                chain.doFilter(request, response);
            } else if (usuario.getStatusUsuario().equals("a")) {
            ((HttpServletRequest) request).setAttribute("msg", "Você não tem permissão de acesso à essa área!");
            ((HttpServletRequest) request).getRequestDispatcher("/principal.jsp").forward(request, response);
            }
        } else {
            ((HttpServletRequest)request).setAttribute("msg", "Usuário não logado! Efetue login acima.");
            ((HttpServletRequest)request).getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
