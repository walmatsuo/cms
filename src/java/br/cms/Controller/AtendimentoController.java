package br.cms.Controller;

import br.cms.Interface.Executa;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AtendimentoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String parametro = request.getParameter("action");
        String nomeDaClasse = "br.cms.Controller.Atendimento." + parametro;

        try {
            Class classe = Class.forName(nomeDaClasse);

            Executa executa = (Executa) classe.newInstance();
            String pagina = executa.executa(request, response);

            // Exportar em PDF, retorno é null
            if (pagina != null) {
                request.getRequestDispatcher(pagina).forward(request, response);
            }

        } catch (ClassNotFoundException cnfe) {
            request.setAttribute("msg", "Função indisponível! Tente novamente!");

            request.getRequestDispatcher("/principal.jsp").forward(request, response);
        } catch (Exception erro) {
            request.setAttribute("msg", erro.getMessage());

            request.getRequestDispatcher("/principal.jsp").forward(request, response);
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
