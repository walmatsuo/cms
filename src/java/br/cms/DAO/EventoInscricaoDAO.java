package br.cms.DAO;

import br.cms.Model.EventoInscricao;
import br.cms.Model.Evento;
import br.cms.Model.Socio;
import br.cms.BD.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventoInscricaoDAO {

    private static final String CADASTRAR =                 "INSERT INTO evento_inscricao "
                                                            + "(evento,socio,status_evento_inscricao) "
                                                            + "VALUES (?,?,?)";
    private static final String LISTARTODOSINSCRITOS =      "SELECT * FROM evento_inscricao WHERE evento=? AND status_evento_inscricao='a' ORDER BY socio";
    private static final String LISTARTODOSEVENTOS =        "SELECT * FROM evento_inscricao WHERE socio=? AND status_evento_inscricao='a' ORDER BY id_evento_inscricao";
    private static final String ATUALIZARSTATUSPOREVENTO =  "UPDATE evento_inscricao SET status_evento_inscricao=? WHERE evento=?";
    private static final String ATUALIZARSTATUSPORSOCIO =   "UPDATE evento_inscricao SET status_evento_inscricao=? WHERE socio=?";

    public void cadastrar(EventoInscricao eventoInscricao) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);

            pstmt.setLong(1, eventoInscricao.getEvento().getIdEvento());
            pstmt.setLong(2, eventoInscricao.getSocio().getIdSocio());
            pstmt.setString(3, eventoInscricao.getStatusEventoInscricao());

            pstmt.execute();

            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public ArrayList<EventoInscricao> listarTodosInscritos(EventoInscricao eventoInscricao) {

        Connection conexao = null;
        ArrayList<EventoInscricao> listaEventoInscricao = new ArrayList<EventoInscricao>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARTODOSINSCRITOS);

            pstmt.setLong(1, eventoInscricao.getEvento().getIdEvento());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
                eventoInscricaoOBJ.setIdEventoInscricao(rs.getLong("id_evento_inscricao"));

                Evento eventoOBJ = new Evento();
                eventoOBJ.setIdEvento(rs.getLong("evento"));

                eventoInscricaoOBJ.setEvento(eventoOBJ);

                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("socio"));

                eventoInscricaoOBJ.setSocio(socioOBJ);

                listaEventoInscricao.add(eventoInscricaoOBJ);
            }

            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return listaEventoInscricao;
    }

    public ArrayList<EventoInscricao> listarTodosEventos(EventoInscricao eventoInscricao) {

        Connection conexao = null;
        ArrayList<EventoInscricao> listaEventoInscrito = new ArrayList<EventoInscricao>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARTODOSEVENTOS);

            pstmt.setLong(1, eventoInscricao.getSocio().getIdSocio());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                EventoInscricao eventoInscricaoOBJ = new EventoInscricao();
                eventoInscricaoOBJ.setIdEventoInscricao(rs.getLong("id_evento_inscricao"));

                Evento eventoOBJ = new Evento();
                eventoOBJ.setIdEvento(rs.getInt("evento"));

                eventoInscricaoOBJ.setEvento(eventoOBJ);

                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("socio"));

                eventoInscricaoOBJ.setSocio(socioOBJ);

                listaEventoInscrito.add(eventoInscricaoOBJ);
            }

            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return listaEventoInscrito;
    }

    public void alterarStatusPorEvento(EventoInscricao eventoInscricao) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUSPOREVENTO);

            pstmt.setString(1, eventoInscricao.getStatusEventoInscricao());
            pstmt.setLong(2, eventoInscricao.getEvento().getIdEvento());

            pstmt.execute();

            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void alterarStatusPorSocio(EventoInscricao eventoInscricao) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUSPORSOCIO);

            pstmt.setString(1, eventoInscricao.getStatusEventoInscricao());
            pstmt.setLong(2, eventoInscricao.getSocio().getIdSocio());

            pstmt.execute();

            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
