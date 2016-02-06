package br.cms.DAO;

import br.cms.BD.ConectaBanco;
import br.cms.Model.Espaco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EspacoDAO {

    private static final String CADASTRAR =         "INSERT INTO espaco "
                                                    + "(nome_espaco, abertura, fechamento, descricao_espaco, status_espaco) "
                                                    + "VALUES (?,?,?,?,?)";
    private static final String LISTARTODOS =       "SELECT * FROM espaco WHERE status_espaco='a' ORDER BY nome_espaco";
    private static final String PESQUISAR =         "SELECT * FROM espaco WHERE status_espaco='a' AND nome_espaco ILIKE ? ORDER BY nome_espaco";
    private static final String BUSCARPORID =       "SELECT * FROM espaco WHERE status_espaco='a' AND id_espaco=?";
    private static final String QTDEPORSTATUS =     "SELECT COUNT(id_espaco) FROM espaco WHERE status_espaco=?";
    private static final String ATUALIZAR =         "UPDATE espaco "
                                                    + "SET (nome_espaco, abertura, fechamento, descricao_espaco) = "
                                                    + "(?,?,?,?) WHERE id_espaco=?";
    private static final String ATUALIZARSTATUS =   "UPDATE espaco SET status_espaco=? WHERE id_espaco=?";

    public void cadastrar(Espaco espaco) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);

            pstmt.setString(1, espaco.getNomeEspaco());
            pstmt.setInt(2, espaco.getAbertura());
            pstmt.setInt(3, espaco.getFechamento());
            pstmt.setString(4, espaco.getDescricaoEspaco());
            pstmt.setString(5, espaco.getStatusEspaco());

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

    public ArrayList<Espaco> listarTodos() {

        Connection conexao = null;
        ArrayList<Espaco> listaEspaco = new ArrayList<Espaco>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARTODOS);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Espaco espacoOBJ = new Espaco();
                espacoOBJ.setIdEspaco(rs.getLong("id_espaco"));
                espacoOBJ.setNomeEspaco(rs.getString("nome_espaco"));
                espacoOBJ.setAbertura(rs.getInt("abertura"));
                espacoOBJ.setFechamento(rs.getInt("fechamento"));
                espacoOBJ.setDescricaoEspaco(rs.getString("descricao_espaco"));
                espacoOBJ.setStatusEspaco(rs.getString("status_espaco"));

                listaEspaco.add(espacoOBJ);
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
        return listaEspaco;
    }

    public ArrayList<Espaco> pesquisar(Espaco espaco) {

        Connection conexao = null;
        ArrayList<Espaco> listaEspaco = new ArrayList<Espaco>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(PESQUISAR);

            pstmt.setString(1, "%" + espaco.getNomeEspaco() + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Espaco espacoOBJ = new Espaco();
                espacoOBJ.setIdEspaco(rs.getLong("id_espaco"));
                espacoOBJ.setNomeEspaco(rs.getString("nome_espaco"));
                espacoOBJ.setAbertura(rs.getInt("abertura"));
                espacoOBJ.setFechamento(rs.getInt("fechamento"));
                espacoOBJ.setDescricaoEspaco(rs.getString("descricao_espaco"));
                espacoOBJ.setStatusEspaco(rs.getString("status_espaco"));

                listaEspaco.add(espacoOBJ);
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
        return listaEspaco;
    }

    public Espaco consultarPorId(Espaco espaco) {

        Connection conexao = null;
        Espaco espacoOBJ = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORID);

            pstmt.setLong(1, espaco.getIdEspaco());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                espacoOBJ = new Espaco();
                espacoOBJ.setIdEspaco(rs.getLong("id_espaco"));
                espacoOBJ.setNomeEspaco(rs.getString("nome_espaco"));
                espacoOBJ.setAbertura(rs.getInt("abertura"));
                espacoOBJ.setFechamento(rs.getInt("fechamento"));
                espacoOBJ.setDescricaoEspaco(rs.getString("descricao_espaco"));
                espacoOBJ.setStatusEspaco(rs.getString("status_espaco"));
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
        return espacoOBJ;
    }

    public int retornarQuantidadePorStatus(Espaco espaco) {

        Connection conexao = null;
        int quantidadeEspaco = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(QTDEPORSTATUS);

            pstmt.setString(1, espaco.getStatusEspaco());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                quantidadeEspaco = rs.getInt(1);
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
        return quantidadeEspaco;
    }

    public void alterar(Espaco espaco) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);

            pstmt.setString(1, espaco.getNomeEspaco());
            pstmt.setInt(2, espaco.getAbertura());
            pstmt.setInt(3, espaco.getFechamento());
            pstmt.setString(4, espaco.getDescricaoEspaco());
            pstmt.setLong(5, espaco.getIdEspaco());

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

    public void alterarStatus(Espaco espaco) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUS);

            pstmt.setString(1, espaco.getStatusEspaco());
            pstmt.setLong(2, espaco.getIdEspaco());

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
