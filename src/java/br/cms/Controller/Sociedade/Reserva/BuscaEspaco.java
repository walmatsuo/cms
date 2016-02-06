package br.cms.Controller.Sociedade.Reserva;

import br.cms.DAO.EspacoDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Espaco;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class BuscaEspaco implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        EspacoDAO espacoDAO = new EspacoDAO();
        ArrayList<Espaco> listaEspaco = espacoDAO.listarTodos();

        JSONArray jsonArray = new JSONArray();

        for (Espaco espaco : listaEspaco) {
            jsonArray.put(new JSONObject(espaco));
        }

        return jsonArray.toString();
    }
}
