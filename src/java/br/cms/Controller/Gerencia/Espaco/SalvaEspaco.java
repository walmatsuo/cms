package br.cms.Controller.Gerencia.Espaco;

import br.cms.DAO.EspacoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Espaco;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalvaEspaco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "GerenciaController?action=Espaco.ListaEspaco";
        
        String idEspaco = req.getParameter("idEspaco");
        String nomeEspaco = req.getParameter("nomeEspaco");
        int abertura = Integer.parseInt(req.getParameter("abertura"));
        int fechamento = Integer.parseInt(req.getParameter("fechamento"));
        String descricaoEspaco = req.getParameter("descricaoEspaco");

        if (idEspaco.equals("")) {
            Espaco espacoOBJ = new Espaco();
            espacoOBJ.setNomeEspaco(nomeEspaco);
            espacoOBJ.setAbertura(abertura);
            espacoOBJ.setFechamento(fechamento);
            espacoOBJ.setDescricaoEspaco(descricaoEspaco);
            espacoOBJ.setStatusEspaco("a");

            EspacoDAO espacoDAO = new EspacoDAO();
            espacoDAO.cadastrar(espacoOBJ);

            req.setAttribute("msg", "Espaço " + espacoOBJ.getNomeEspaco() + " cadastrado com sucesso!");
        } else {
            Espaco espacoOBJ = new Espaco();
            espacoOBJ.setIdEspaco(Long.parseLong(idEspaco));
            espacoOBJ.setNomeEspaco(nomeEspaco);
            espacoOBJ.setAbertura(abertura);
            espacoOBJ.setFechamento(fechamento);
            espacoOBJ.setDescricaoEspaco(descricaoEspaco);
            
            EspacoDAO espacoDAO = new EspacoDAO();
            espacoDAO.alterar(espacoOBJ);

            req.setAttribute("msg", "Espaço " + espacoOBJ.getNomeEspaco() + " atualizado com sucesso!");
        }

        return pagina;
    }
}
