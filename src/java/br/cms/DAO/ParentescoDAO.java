package br.cms.DAO;

import br.cms.Model.Parentesco;
import br.cms.BD.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParentescoDAO {

    private static final String CADASTRAR =         "INSERT INTO parentesco "
                                                    + "(grau_parentesco,nome_parentesco,status_parentesco) "
                                                    + "VALUES (?,?,?)";
    private static final String LISTARTODOS =       "SELECT * FROM parentesco WHERE status_parentesco='a' ORDER BY nome_parentesco";
    private static final String PESQUISAR =         "SELECT * FROM parentesco WHERE status_parentesco='a' AND nome_parentesco ILIKE ? ORDER BY nome_parentesco";
    private static final String BUSCARPORID =       "SELECT * FROM parentesco WHERE status_parentesco='a' AND id_parentesco=?";
    private static final String QTDEPORSTATUS =     "SELECT COUNT(id_parentesco) FROM parentesco WHERE status_parentesco=?";
    private static final String ATUALIZAR =         "UPDATE parentesco "
                                                    + "SET (grau_parentesco,nome_parentesco) = "
                                                    + "(?,?) WHERE id_parentesco=?";
    private static final String ATUALIZARSTATUS =   "UPDATE parentesco SET status_parentesco=? WHERE id_parentesco=?";

    public void cadastrar(Parentesco parentesco) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);

            pstmt.setInt(1, parentesco.getGrauParentesco());
            pstmt.setString(2, parentesco.getNomeParentesco());
            pstmt.setString(3, parentesco.getStatusParentesco());

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

    public ArrayList<Parentesco> listarTodos() {

        Connection conexao = null;
        ArrayList<Parentesco> listaParentesco = new ArrayList<Parentesco>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARTODOS);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Parentesco parentescoOBJ = new Parentesco();
                parentescoOBJ.setIdParentesco(rs.getInt("id_parentesco"));
                parentescoOBJ.setGrauParentesco(rs.getInt("grau_parentesco"));
                parentescoOBJ.setNomeParentesco(rs.getString("nome_parentesco"));
                parentescoOBJ.setStatusParentesco(rs.getString("status_parentesco"));

                listaParentesco.add(parentescoOBJ);
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
        return listaParentesco;
    }

    public ArrayList<Parentesco> pesquisar(Parentesco parentesco) {

        Connection conexao = null;
        ArrayList<Parentesco> listaParentesco = new ArrayList<Parentesco>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(PESQUISAR);

            pstmt.setString(1, "%" + parentesco.getNomeParentesco() + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Parentesco parentescoOBJ = new Parentesco();
                parentescoOBJ.setIdParentesco(rs.getInt("id_parentesco"));
                parentescoOBJ.setGrauParentesco(rs.getInt("grau_parentesco"));
                parentescoOBJ.setNomeParentesco(rs.getString("nome_parentesco"));
                parentescoOBJ.setStatusParentesco(rs.getString("status_parentesco"));

                listaParentesco.add(parentescoOBJ);
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
        return listaParentesco;
    }

    public Parentesco consultarPorId(Parentesco parentesco) {

        Connection conexao = null;
        Parentesco parentescoOBJ = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORID);

            pstmt.setLong(1, parentesco.getIdParentesco());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                parentescoOBJ = new Parentesco();
                parentescoOBJ.setIdParentesco(rs.getInt("id_parentesco"));
                parentescoOBJ.setGrauParentesco(rs.getInt("grau_parentesco"));
                parentescoOBJ.setNomeParentesco(rs.getString("nome_parentesco"));
                parentescoOBJ.setStatusParentesco(rs.getString("status_parentesco"));
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
        return parentescoOBJ;
    }

    public int retornarQuantidadePorStatus(Parentesco parentesco) {

        Connection conexao = null;
        int quantidadeParentesco = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(QTDEPORSTATUS);

            pstmt.setString(1, parentesco.getStatusParentesco());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                quantidadeParentesco = rs.getInt(1);
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
        return quantidadeParentesco;
    }

    public void alterar(Parentesco parentesco) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);

            pstmt.setInt(1, parentesco.getGrauParentesco());
            pstmt.setString(2, parentesco.getNomeParentesco());
            pstmt.setInt(3, parentesco.getIdParentesco());

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

    public void alterarStatus(Parentesco parentesco) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUS);

            pstmt.setString(1, parentesco.getStatusParentesco());
            pstmt.setLong(2, parentesco.getIdParentesco());

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
