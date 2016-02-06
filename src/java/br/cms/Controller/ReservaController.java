package br.cms.Controller;

import br.cms.Interface.Executa;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String parametro = request.getParameter("action");
        String nomeDaClasse = "br.cms.Controller.Sociedade." + parametro;

        try {
            Class classe = Class.forName(nomeDaClasse);

            Executa executa = (Executa) classe.newInstance();
            String jsonOuPagina = executa.executa(request, response);

            if (jsonOuPagina != null) {
                if (jsonOuPagina.endsWith(".jsp")) {
                    request.getRequestDispatcher(jsonOuPagina).forward(request, response);
                } else {
                    response.getWriter().println(jsonOuPagina);
                }
            } else {
                response.getWriter().println(request.getAttribute("msg"));
            }

        } catch (ClassNotFoundException cnfe) {
            request.setAttribute("msg", "Função indisponível! Tente novamente!");

            request.getRequestDispatcher("/principal.jsp").forward(request, response);
        } catch (Exception erro) {
            response.getWriter().println(erro.getMessage());
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
