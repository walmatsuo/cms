package br.cms.Controller.Sociedade.Reserva;

import br.cms.DAO.EspacoDAO;
import br.cms.DAO.ReservaDAO;
import br.cms.DAO.SocioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.CategoriaUsuario;
import br.cms.Model.Espaco;
import br.cms.Model.Reserva;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListaReserva implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        long idSocio = 0;
        long idEspaco = 0;

        HttpSession sessaoUsuario = ((HttpServletRequest) req).getSession();
        Usuario usuarioOBJ = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

        if (usuarioOBJ.getCategoriaUsuario().equals(CategoriaUsuario.Sociedade)) {
            idSocio = usuarioOBJ.getLogin();
        } else {
            Espaco espacoOBJ = (Espaco) sessaoUsuario.getAttribute("espacoReserva");
            idEspaco = espacoOBJ.getIdEspaco();
        }

        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(idSocio);

        Espaco espacoOBJ = new Espaco();
        espacoOBJ.setIdEspaco(idEspaco);

        Reserva reservaOBJ = new Reserva();
        reservaOBJ.setSocioReserva(socioOBJ);
        reservaOBJ.setEspacoReserva(espacoOBJ);

        // Recuperando Reservas encontradas
        ReservaDAO reservaDAO = new ReservaDAO();
        ArrayList<Reserva> listaReserva = reservaDAO.listarPorEspacoSocio(reservaOBJ);

        // Recuperando dados do Espaço e Sócio das Reservas
        for (Reserva reserva : listaReserva) {
            Espaco espaco = new Espaco();
            espaco.setIdEspaco(reserva.getEspacoReserva().getIdEspaco());

            Socio socio = new Socio();
            socio.setIdSocio(reserva.getSocioReserva().getIdSocio());

            EspacoDAO espacoDAO1 = new EspacoDAO();
            espaco = espacoDAO1.consultarPorId(espaco);

            SocioDAO socioDAO1 = new SocioDAO();
            socio = socioDAO1.consultarPorId(socio);

            reserva.setEspacoReserva(espaco);
            reserva.setSocioReserva(socio);
        }

        JSONArray jsonArray = new JSONArray();

        for (Reserva reserva : listaReserva) {
            jsonArray.put(new JSONObject(reserva));
        }

        return jsonArray.toString();
    }
}
