package br.cms.Controller.Sociedade.Reserva;

import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Socio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class BuscaSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        SocioDAO socioDAO = new SocioDAO();
        ArrayList<Socio> listaSocio = socioDAO.listarTodos();

        JSONArray jsonArray = new JSONArray();

        for (Socio socio : listaSocio) {
            jsonArray.put(new JSONObject(socio));
        }

        return jsonArray.toString();
    }
}
