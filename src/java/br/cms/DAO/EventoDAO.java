package br.cms.DAO;

import br.cms.Model.Evento;
import br.cms.BD.ConectaBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventoDAO {

    private static final String CADASTRAR =         "INSERT INTO evento "
                                                    + "(data_evento,titulo_evento,descricao_evento,local_evento,status_evento) "
                                                    + "VALUES (?,?,?,?,?)";
    private static final String LISTARTODOS =       "SELECT * FROM evento WHERE status_evento='a' ORDER BY titulo_evento";
    private static final String PESQUISAR =         "SELECT * FROM evento WHERE status_evento='a' AND titulo_evento ILIKE ? ORDER BY titulo_evento";
    private static final String BUSCARPORID =       "SELECT * FROM evento WHERE status_evento='a' AND id_evento=?";
    private static final String ATUALIZAR =         "UPDATE evento "
                                                    + "SET (data_evento,titulo_evento,descricao_evento,local_evento) = "
                                                    + "(?,?,?,?) WHERE id_evento=?";
    private static final String ATUALIZARSTATUS =   "UPDATE evento SET status_evento=? WHERE id_evento=?";

    public void cadastrar(Evento evento) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);

            pstmt.setDate(1, new Date(evento.getData().getTime()));
            pstmt.setString(2, evento.getTitulo());
            pstmt.setString(3, evento.getDescricao());
            pstmt.setString(4, evento.getLocal());
            pstmt.setString(5, evento.getStatusEvento());

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

    public ArrayList<Evento> listarTodos() {

        Connection conexao = null;
        ArrayList<Evento> listaEvento = new ArrayList<Evento>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARTODOS);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Evento eventoOBJ = new Evento();
                eventoOBJ.setIdEvento(rs.getLong("id_evento"));
                eventoOBJ.setData(rs.getDate("data_evento"));
                eventoOBJ.setTitulo(rs.getString("titulo_evento"));
                eventoOBJ.setDescricao(rs.getString("descricao_evento"));
                eventoOBJ.setLocal(rs.getString("local_evento"));
                eventoOBJ.setStatusEvento(rs.getString("status_evento"));

                listaEvento.add(eventoOBJ);
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
        return listaEvento;
    }

    public ArrayList<Evento> pesquisar(Evento evento) {

        Connection conexao = null;
        ArrayList<Evento> listaEvento = new ArrayList<Evento>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(PESQUISAR);

            pstmt.setString(1, "%" + evento.getTitulo() + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Evento eventoOBJ = new Evento();
                eventoOBJ.setIdEvento(rs.getLong("id_evento"));
                eventoOBJ.setTitulo(rs.getString("titulo_evento"));
                eventoOBJ.setData(rs.getDate("data_evento"));
                eventoOBJ.setDescricao(rs.getString("descricao_evento"));
                eventoOBJ.setLocal(rs.getString("local_evento"));
                eventoOBJ.setStatusEvento(rs.getString("status_evento"));

                listaEvento.add(eventoOBJ);
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
        return listaEvento;
    }

    public Evento consultarPorId(Evento evento) {

        Connection conexao = null;
        Evento eventoOBJ = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORID);

            pstmt.setLong(1, evento.getIdEvento());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                eventoOBJ = new Evento();
                eventoOBJ.setIdEvento(rs.getLong("id_evento"));
                eventoOBJ.setData(rs.getDate("data_evento"));
                eventoOBJ.setTitulo(rs.getString("titulo_evento"));
                eventoOBJ.setDescricao(rs.getString("descricao_evento"));
                eventoOBJ.setLocal(rs.getString("local_evento"));
                eventoOBJ.setStatusEvento(rs.getString("status_evento"));
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
        return eventoOBJ;
    }

    public void alterar(Evento evento) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);

            pstmt.setDate(1, new Date(evento.getData().getTime()));
            pstmt.setString(2, evento.getTitulo());
            pstmt.setString(3, evento.getDescricao());
            pstmt.setString(4, evento.getLocal());
            pstmt.setLong(5, evento.getIdEvento());

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

    public void alterarStatus(Evento evento) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUS);

            pstmt.setString(1, evento.getStatusEvento());
            pstmt.setLong(2, evento.getIdEvento());

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
