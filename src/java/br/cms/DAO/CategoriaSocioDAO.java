package br.cms.DAO;

import br.cms.BD.ConectaBanco;
import br.cms.Model.CategoriaSocio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaSocioDAO {

    private static final String CADASTRAR =         "INSERT INTO categoriasocio "
                                                    + "(nome_categoria_socio, valor_titulo, valor_mensalidade, lotacao_minima, lotacao_maxima, status_categoria_socio) "
                                                    + "VALUES (?,?,?,?,?,?)";
    private static final String LISTARTODOS =       "SELECT * FROM categoriasocio WHERE status_categoria_socio='a' ORDER BY nome_categoria_socio";
    private static final String PESQUISAR =         "SELECT * FROM categoriasocio WHERE status_categoria_socio='a' AND nome_categoria_socio ILIKE ? ORDER BY nome_categoria_socio";
    private static final String BUSCARPORID =       "SELECT * FROM categoriasocio WHERE status_categoria_socio='a' AND id_categoria_socio=?";
    private static final String QTDEPORSTATUS =     "SELECT COUNT(id_categoria_socio) FROM categoriasocio WHERE status_categoria_socio=?";
    private static final String ATUALIZAR =         "UPDATE categoriasocio "
                                                    + "SET (nome_categoria_socio, valor_titulo,valor_mensalidade, lotacao_minima, lotacao_maxima) = "
                                                    + "(?,?,?,?,?) WHERE id_categoria_socio=?";
    private static final String ATUALIZARSTATUS =   "UPDATE categoriasocio "
                                                    + "SET status_categoria_socio=? "
                                                    + "WHERE id_categoria_socio=?";

    public void cadastrar(CategoriaSocio categoriaSocio) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);

            pstmt.setString(1, categoriaSocio.getNomeCategoriaSocio());
            pstmt.setDouble(2, categoriaSocio.getValorTitulo());
            pstmt.setDouble(3, categoriaSocio.getValorMensalidade());
            pstmt.setInt(4, categoriaSocio.getLotacaoMinima());
            pstmt.setInt(5, categoriaSocio.getLotacaoMaxima());
            pstmt.setString(6, categoriaSocio.getStatusCategoria());

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

    public ArrayList<CategoriaSocio> listarTodos() {

        Connection conexao = null;
        ArrayList<CategoriaSocio> listaCategoria = new ArrayList<CategoriaSocio>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARTODOS);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
                categoriaSocioOBJ.setIdCategoriaSocio(rs.getLong("id_categoria_socio"));
                categoriaSocioOBJ.setNomeCategoriaSocio(rs.getString("nome_categoria_socio"));
                categoriaSocioOBJ.setValorTitulo(rs.getDouble("valor_titulo"));
                categoriaSocioOBJ.setValorMensalidade(rs.getDouble("valor_mensalidade"));
                categoriaSocioOBJ.setLotacaoMinima(rs.getInt("lotacao_minima"));
                categoriaSocioOBJ.setLotacaoMaxima(rs.getInt("lotacao_maxima"));
                categoriaSocioOBJ.setStatusCategoria(rs.getString("status_categoria_socio"));

                listaCategoria.add(categoriaSocioOBJ);
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
        return listaCategoria;
    }

    public ArrayList<CategoriaSocio> pesquisar(CategoriaSocio categoriaSocio) {

        Connection conexao = null;
        ArrayList<CategoriaSocio> listaCategoria = new ArrayList<CategoriaSocio>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(PESQUISAR);

            pstmt.setString(1, "%" + categoriaSocio.getNomeCategoriaSocio() + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
                categoriaSocioOBJ.setIdCategoriaSocio(rs.getLong("id_categoria_socio"));
                categoriaSocioOBJ.setNomeCategoriaSocio(rs.getString("nome_categoria_socio"));
                categoriaSocioOBJ.setValorTitulo(rs.getDouble("valor_titulo"));
                categoriaSocioOBJ.setValorMensalidade(rs.getDouble("valor_mensalidade"));
                categoriaSocioOBJ.setLotacaoMinima(rs.getInt("lotacao_minima"));
                categoriaSocioOBJ.setLotacaoMaxima(rs.getInt("lotacao_maxima"));
                categoriaSocioOBJ.setStatusCategoria(rs.getString("status_categoria_socio"));

                listaCategoria.add(categoriaSocioOBJ);
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
        return listaCategoria;
    }

    public CategoriaSocio consultarPorId(CategoriaSocio categoriaSocio) {

        Connection conexao = null;
        CategoriaSocio categoriaSocioOBJ = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORID);

            pstmt.setLong(1, categoriaSocio.getIdCategoriaSocio());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                categoriaSocioOBJ = new CategoriaSocio();
                categoriaSocioOBJ.setIdCategoriaSocio(rs.getLong("id_categoria_socio"));
                categoriaSocioOBJ.setNomeCategoriaSocio(rs.getString("nome_categoria_socio"));
                categoriaSocioOBJ.setValorTitulo(rs.getDouble("valor_titulo"));
                categoriaSocioOBJ.setValorMensalidade(rs.getDouble("valor_mensalidade"));
                categoriaSocioOBJ.setLotacaoMinima(rs.getInt("lotacao_minima"));
                categoriaSocioOBJ.setLotacaoMaxima(rs.getInt("lotacao_maxima"));
                categoriaSocioOBJ.setStatusCategoria(rs.getString("status_categoria_socio"));
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
        return categoriaSocioOBJ;
    }

    public int retornarQuantidadePorStatus(CategoriaSocio categoriaSocio) {

        Connection conexao = null;
        int quantidadeCategoria = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(QTDEPORSTATUS);

            pstmt.setString(1, categoriaSocio.getStatusCategoria());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                quantidadeCategoria = rs.getInt(1);
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
        return quantidadeCategoria;
    }

    public void alterar(CategoriaSocio categoriaSocio) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);

            pstmt.setString(1, categoriaSocio.getNomeCategoriaSocio());
            pstmt.setDouble(2, categoriaSocio.getValorTitulo());
            pstmt.setDouble(3, categoriaSocio.getValorMensalidade());
            pstmt.setInt(4, categoriaSocio.getLotacaoMinima());
            pstmt.setInt(5, categoriaSocio.getLotacaoMaxima());
            pstmt.setLong(6, categoriaSocio.getIdCategoriaSocio());

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

    public void alterarStatus(CategoriaSocio categoriaSocio) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUS);

            pstmt.setString(1, categoriaSocio.getStatusCategoria());
            pstmt.setLong(2, categoriaSocio.getIdCategoriaSocio());

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
