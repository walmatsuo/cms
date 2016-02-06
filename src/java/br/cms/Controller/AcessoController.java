package br.cms.Controller;

import br.cms.Interface.Executa;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcessoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String parametro = request.getParameter("action");
        String nomeDaClasse = "br.cms.Controller.Acesso." + parametro;

        try {
            Class classe = Class.forName(nomeDaClasse);

            Executa executa = (Executa) classe.newInstance();
            String pagina = executa.executa(request, response);

            request.getRequestDispatcher(pagina).forward(request, response);

        } catch (ClassNotFoundException cnfe) {
            request.setAttribute("msg", "Função indisponível! Tente novamente!");

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception erro) {
            //throw new ServletException(erro);
            request.setAttribute("msg", erro.getMessage());

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
