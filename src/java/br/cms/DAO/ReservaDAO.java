package br.cms.DAO;

import br.cms.Model.Espaco;
import br.cms.Model.Reserva;
import br.cms.Model.Socio;
import br.cms.BD.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ReservaDAO {

    private static final String CADASTRAR =                 "INSERT INTO reserva "
                                                            + "(titulo_reserva,inicio_reserva,fim_reserva,status_reserva,espaco,socio) "
                                                            + "VALUES (?,?,?,?,?,?)";
    private static final String LISTARPORSTATUS =           "SELECT * FROM reserva WHERE status_reserva=? ORDER BY inicio_reserva";
    private static final String LISTARPORESPACOSOCIO =      "SELECT * FROM reserva WHERE status_reserva IN ('a','c','k') AND espaco=? OR socio=? ORDER BY inicio_reserva";
    private static final String LISTAROCUPADOS =            "SELECT * FROM reserva WHERE status_reserva='a' AND espaco=? AND inicio_reserva>=? AND fim_reserva<=?  ORDER BY inicio_reserva";
    private static final String QTDEPORESPACO =             "SELECT COUNT(id_reserva) FROM reserva WHERE status_reserva=? AND espaco=?";
    private static final String QTDEPORSOCIO =              "SELECT COUNT(id_reserva) FROM reserva WHERE status_reserva=? AND socio=?";
    private static final String VALIDAINTERVALO =           "SELECT COUNT(id_reserva) FROM reserva WHERE status_reserva=? AND inicio_reserva<? AND fim_reserva>? AND espaco=?";
    private static final String ATUALIZARSTATUS =           "UPDATE reserva SET status_reserva=? WHERE id_reserva=?";
    private static final String ATUALIZARSTATUSPORSOCIO =   "UPDATE reserva SET status_reserva=? WHERE socio=?";

    public void cadastrar(Reserva reserva) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);

            pstmt.setString(1, reserva.getTitle());
            pstmt.setTimestamp(2, new Timestamp(reserva.getStart().getTime()));
            pstmt.setTimestamp(3, new Timestamp(reserva.getEnd().getTime()));
            pstmt.setString(4, reserva.getStatusReserva());
            pstmt.setLong(5, reserva.getEspacoReserva().getIdEspaco());
            pstmt.setLong(6, reserva.getSocioReserva().getIdSocio());

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

    public ArrayList<Reserva> listarPorStatus(Reserva reserva) {

        Connection conexao = null;
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARPORSTATUS);

            pstmt.setString(1, reserva.getStatusReserva());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva reservaOBJ = new Reserva();
                reservaOBJ.setId(rs.getLong("id_reserva"));
                reservaOBJ.setTitle(rs.getString("titulo_reserva"));
                //reservaOBJ.setAllDay(rs.getBoolean("diainteiro_reserva"));
                reservaOBJ.setStart(rs.getTimestamp("inicio_reserva"));
                reservaOBJ.setEnd(rs.getTimestamp("fim_reserva"));
                reservaOBJ.setStatusReserva(rs.getString("status_reserva"));

                Espaco espacoOBJ = new Espaco();
                espacoOBJ.setIdEspaco(rs.getLong("espaco"));

                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("socio"));

                reservaOBJ.setEspacoReserva(espacoOBJ);
                reservaOBJ.setSocioReserva(socioOBJ);

                listaReserva.add(reservaOBJ);
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
        return listaReserva;
    }

    public ArrayList<Reserva> listarPorEspacoSocio(Reserva reserva) {

        Connection conexao = null;
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARPORESPACOSOCIO);

            pstmt.setLong(1, reserva.getEspacoReserva().getIdEspaco());
            pstmt.setLong(2, reserva.getSocioReserva().getIdSocio());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva reservaOBJ = new Reserva();
                reservaOBJ.setId(rs.getLong("id_reserva"));
                reservaOBJ.setTitle(rs.getString("titulo_reserva"));
                //reservaOBJ.setAllDay(rs.getBoolean("diainteiro_reserva"));
                reservaOBJ.setStart(rs.getTimestamp("inicio_reserva"));
                reservaOBJ.setEnd(rs.getTimestamp("fim_reserva"));
                reservaOBJ.setStatusReserva(rs.getString("status_reserva"));

                Espaco espacoOBJ = new Espaco();
                espacoOBJ.setIdEspaco(rs.getLong("espaco"));

                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("socio"));

                reservaOBJ.setEspacoReserva(espacoOBJ);
                reservaOBJ.setSocioReserva(socioOBJ);

                listaReserva.add(reservaOBJ);
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
        return listaReserva;
    }

    public ArrayList<Reserva> listarOcupados(Reserva reserva) {

        Connection conexao = null;
        ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTAROCUPADOS);

            pstmt.setLong(1, reserva.getEspacoReserva().getIdEspaco());
            pstmt.setTimestamp(2, new Timestamp(reserva.getStart().getTime()));
            pstmt.setTimestamp(3, new Timestamp(reserva.getEnd().getTime()));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Reserva reservaOBJ = new Reserva();
                reservaOBJ.setId(rs.getLong("id_reserva"));
                reservaOBJ.setTitle(rs.getString("titulo_reserva"));
                //reservaOBJ.setAllDay(rs.getBoolean("diainteiro_reserva"));
                reservaOBJ.setStart(rs.getTimestamp("inicio_reserva"));
                reservaOBJ.setEnd(rs.getTimestamp("fim_reserva"));
                reservaOBJ.setStatusReserva(rs.getString("status_reserva"));

                Espaco espacoOBJ = new Espaco();
                espacoOBJ.setIdEspaco(rs.getLong("espaco"));

                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("socio"));

                reservaOBJ.setEspacoReserva(espacoOBJ);
                reservaOBJ.setSocioReserva(socioOBJ);

                listaReserva.add(reservaOBJ);
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
        return listaReserva;
    }

    public int retornarQuantidadePorEspaco(Reserva reserva) {

        Connection conexao = null;
        int quantidadeReserva = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(QTDEPORESPACO);

            pstmt.setString(1, reserva.getStatusReserva());
            pstmt.setLong(2, reserva.getEspacoReserva().getIdEspaco());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                quantidadeReserva = rs.getInt(1);
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
        return quantidadeReserva;
    }

    public int retornarQuantidadePorSocio(Reserva reserva) {

        Connection conexao = null;
        int quantidadeReserva = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(QTDEPORSOCIO);

            pstmt.setString(1, reserva.getStatusReserva());
            pstmt.setLong(2, reserva.getSocioReserva().getIdSocio());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                quantidadeReserva = rs.getInt(1);
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
        return quantidadeReserva;
    }

    public boolean validarIntervalo(Reserva reserva) {

        boolean disponivel = false;

        Connection conexao = null;

        try {
            int qtdeReserva = 0;

            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(VALIDAINTERVALO);

            pstmt.setString(1, reserva.getStatusReserva());
            pstmt.setTimestamp(2, new Timestamp(reserva.getEnd().getTime()));
            pstmt.setTimestamp(3, new Timestamp(reserva.getStart().getTime()));
            pstmt.setLong(4, reserva.getEspacoReserva().getIdEspaco());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                qtdeReserva = rs.getInt(1);
            }

            if (qtdeReserva == 0) {
                disponivel = true;
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
        return disponivel;
    }

    public void alterarStatus(Reserva reserva) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUS);

            pstmt.setString(1, reserva.getStatusReserva());
            pstmt.setLong(2, reserva.getId());

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

    public void alterarStatusPorSocio(Reserva reserva) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUSPORSOCIO);

            pstmt.setString(1, reserva.getStatusReserva());
            pstmt.setLong(2, reserva.getSocioReserva().getIdSocio());

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
