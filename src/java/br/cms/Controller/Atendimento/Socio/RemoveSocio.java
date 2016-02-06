package br.cms.Controller.Atendimento.Socio;

import br.cms.DAO.DependenteDAO;
import br.cms.DAO.EventoInscricaoDAO;
import br.cms.DAO.ReservaDAO;
import br.cms.DAO.SocioDAO;
import br.cms.DAO.UsuarioDAO;
import br.cms.Interface.Executa;
import br.cms.Model.Dependente;
import br.cms.Model.EventoInscricao;
import br.cms.Model.Reserva;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveSocio implements Executa {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        String pagina = "AtendimentoController?action=Socio.ListaSocio";

        long idSocio = Long.parseLong(req.getParameter("idSocio"));

        Socio socioOBJ = new Socio();
        socioOBJ.setIdSocio(idSocio);

        SocioDAO socioDAO = new SocioDAO();
        socioOBJ = socioDAO.consultarPorId(socioOBJ);
        socioOBJ.setStatusSocio("x");
        
        // Excluindo Sócio
        socioDAO.alterarStatus(socioOBJ);

        // Excluindo Usuário do Sócio
        Usuario usuarioOBJ = socioOBJ.getUsuarioSocio();
        usuarioOBJ.setStatusUsuario("x");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.alterarStatus(usuarioOBJ);

        // Excluindo Dependentes do Sócio
        Dependente dependenteOBJ = new Dependente();
        dependenteOBJ.setSocioDependente(socioOBJ);
        dependenteOBJ.setStatusDependente("x");

        DependenteDAO dependenteDAO = new DependenteDAO();
        dependenteDAO.alterarStatusPorSocio(dependenteOBJ);

        // Excluindo Reservas do Sócio
        Reserva reservaOBJ = new Reserva();
        reservaOBJ.setSocioReserva(socioOBJ);
        reservaOBJ.setStatusReserva("x");

        ReservaDAO reservaDAO = new ReservaDAO();
        reservaDAO.alterarStatusPorSocio(reservaOBJ);
        
        // Excluindo Inscrição em Eventos do Sócio
        EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
        eventoInscricaoOBJ.setSocio(socioOBJ);
        eventoInscricaoOBJ.setStatusEventoInscricao("x");
        
        EventoInscricaoDAO eventoInscricaoDAO = new EventoInscricaoDAO();
        eventoInscricaoDAO.alterarStatusPorSocio(eventoInscricaoOBJ);
        
        req.setAttribute("msg", "Sócio(a) " + socioOBJ.getNomeSocio() + " excluído(a) com sucesso!");

        return pagina;
    }
}
