package br.cms.Controller.Gerencia.CategoriaSocio;

import br.cms.DAO.CategoriaSocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaSocio;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalvaCategoriaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "GerenciaController?action=CategoriaSocio.ListaCategoriaSocio";

        String idCategoriaSocio = req.getParameter("idCategoriaSocio");
        String nomeCategoria = req.getParameter("nomeCategoria");
        double valorTitulo = Double.parseDouble(req.getParameter("valorTitulo"));
        double valorMensalidade = Double.parseDouble(req.getParameter("valorMensalidade"));
        int lotacaoMinima = Integer.parseInt(req.getParameter("lotacaoMinima"));
        int lotacaoMaxima = Integer.parseInt(req.getParameter("lotacaoMaxima"));

        if (idCategoriaSocio.equals("")) {
            CategoriaSocio categoriaOBJ = new CategoriaSocio();
            categoriaOBJ.setNomeCategoriaSocio(nomeCategoria);
            categoriaOBJ.setValorTitulo(valorTitulo);
            categoriaOBJ.setValorMensalidade(valorMensalidade);
            categoriaOBJ.setLotacaoMinima(lotacaoMinima);
            categoriaOBJ.setLotacaoMaxima(lotacaoMaxima);
            categoriaOBJ.setStatusCategoria("a");

            CategoriaSocioDAO categoriaDAO = new CategoriaSocioDAO();
            categoriaDAO.cadastrar(categoriaOBJ);

            req.setAttribute("msg", "Categoria " + categoriaOBJ.getNomeCategoriaSocio() + " cadastrada com sucesso!");
        } else {
            CategoriaSocio categoriaOBJ = new CategoriaSocio();
            categoriaOBJ.setIdCategoriaSocio(Long.parseLong(idCategoriaSocio));
            categoriaOBJ.setNomeCategoriaSocio(nomeCategoria);
            categoriaOBJ.setValorTitulo(valorTitulo);
            categoriaOBJ.setValorMensalidade(valorMensalidade);
            categoriaOBJ.setLotacaoMinima(lotacaoMinima);
            categoriaOBJ.setLotacaoMaxima(lotacaoMaxima);

            CategoriaSocioDAO categoriaDAO = new CategoriaSocioDAO();
            categoriaDAO.alterar(categoriaOBJ);

            req.setAttribute("msg", "Categoria " + categoriaOBJ.getNomeCategoriaSocio() + " atualizada com sucesso!");
        }

        return pagina;
    }
}
