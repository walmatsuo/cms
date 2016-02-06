package br.cms.DAO;

import br.cms.Model.CategoriaUsuario;
import br.cms.Model.Usuario;
import br.cms.BD.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class UsuarioDAO {

    private static final String CADASTRARFUNCIONARIO =      "INSERT INTO usuario "
                                                            + "(login,senha,nome_usuario,categoria_usuario,email,status_usuario) "
                                                            + "VALUES (nextval('seq_login_func'),?,?,?,?,?)";
    private static final String CADASTRARSOCIO =            "INSERT INTO usuario "
                                                            + "(senha,nome_usuario,categoria_usuario,email,status_usuario) "
                                                            + "VALUES (?,?,?,?,?)";
    private static final String LISTARTODOS =               "SELECT * FROM usuario WHERE status_usuario IN ('a','p') AND login < 100000001 ORDER BY nome_usuario";
    private static final String PESQUISAR =                 "SELECT * FROM usuario WHERE status_usuario IN ('a','p') AND login < 100000001 AND nome_usuario ILIKE ? ORDER BY nome_usuario";
    private static final String BUSCARPORID =               "SELECT * FROM usuario WHERE status_usuario IN ('a','p','b') AND id_usuario=?";
    private static final String BUSCARPORLOGINEMAIL =       "SELECT * FROM usuario WHERE status_usuario IN ('a','p') AND login=? AND email=?";
    private static final String AUTENTICA_USUARIO =         "SELECT * FROM usuario WHERE status_usuario IN ('a','p') AND login=? AND senha=?";
    private static final String QTDEPORCATEGORIA =          "SELECT COUNT(id_usuario) FROM usuario WHERE status_usuario IN ('a','p') AND categoria_usuario=?";
    private static final String ATUALIZAR =                 "UPDATE usuario "
                                                            + "SET (nome_usuario,categoria_usuario,email) = "
                                                            + "(?,?,?) WHERE id_usuario=?";
    private static final String ATUALIZARLOGIN =            "UPDATE usuario SET (login) = (?) WHERE id_usuario=?";
    private static final String ATUALIZARSENHASTATUS =      "UPDATE usuario SET (senha,status_usuario) = (?,?) WHERE id_usuario=?";
    private static final String ATUALIZARSTATUS =           "UPDATE usuario SET status_usuario=? WHERE id_usuario=?";

    public long cadastrarFuncionario(Usuario usuario) {

        Connection conexao = null;
        long returnlogin = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(CADASTRARFUNCIONARIO, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, usuario.getSenha());
            pstmt.setString(2, usuario.getNomeUsuario());
            pstmt.setString(3, usuario.getCategoriaUsuario().toString());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getStatusUsuario());

            pstmt.execute();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                returnlogin = rs.getLong("login");
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
        return returnlogin;
    }

    public long cadastrarSocio(Usuario usuario) {

        Connection conexao = null;
        long returnid = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(CADASTRARSOCIO, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, usuario.getSenha());
            pstmt.setString(2, usuario.getNomeUsuario());
            pstmt.setString(3, usuario.getCategoriaUsuario().toString());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getStatusUsuario());

            pstmt.execute();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                returnid = rs.getLong("id_usuario");
            } else {
                returnid = -1;
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
        return returnid;
    }

    public ArrayList<Usuario> listarTodos() {

        Connection conexao = null;
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARTODOS);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario usuarioOBJ = new Usuario();
                usuarioOBJ.setIdUsuario(rs.getLong("id_usuario"));
                usuarioOBJ.setLogin(rs.getLong("login"));
                usuarioOBJ.setNomeUsuario(rs.getString("nome_usuario"));
                usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.valueOf(rs.getString("categoria_usuario")));
                usuarioOBJ.setEmail(rs.getString("email"));

                listaUsuario.add(usuarioOBJ);
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
        return listaUsuario;
    }

    public ArrayList<Usuario> pesquisar(Usuario usuario) {

        Connection conexao = null;
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(PESQUISAR);

            pstmt.setString(1, "%" + usuario.getNomeUsuario() + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario usuarioOBJ = new Usuario();
                usuarioOBJ.setIdUsuario(rs.getLong("id_usuario"));
                usuarioOBJ.setLogin(rs.getLong("login"));
                usuarioOBJ.setNomeUsuario(rs.getString("nome_usuario"));
                usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.valueOf(rs.getString("categoria_usuario")));
                usuarioOBJ.setEmail(rs.getString("email"));

                listaUsuario.add(usuarioOBJ);
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
        return listaUsuario;
    }

    public Usuario consultarPorId(Usuario usuario) {

        Connection conexao = null;
        Usuario usuarioOBJ = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORID);

            pstmt.setLong(1, usuario.getIdUsuario());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuarioOBJ = new Usuario();
                usuarioOBJ.setIdUsuario(rs.getLong("id_usuario"));
                usuarioOBJ.setLogin(rs.getLong("login"));
                usuarioOBJ.setNomeUsuario(rs.getString("nome_usuario"));
                usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.valueOf(rs.getString("categoria_usuario")));
                usuarioOBJ.setEmail(rs.getString("email"));
                usuarioOBJ.setStatusUsuario(rs.getString("status_usuario"));
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
        return usuarioOBJ;
    }

    public Usuario consultarPorLoginEmail(Usuario usuario) {

        Connection conexao = null;
        Usuario usuarioOBJ = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORLOGINEMAIL);

            pstmt.setLong(1, usuario.getLogin());
            pstmt.setString(2, usuario.getEmail());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuarioOBJ = new Usuario();
                usuarioOBJ.setIdUsuario(rs.getLong("id_usuario"));
                usuarioOBJ.setLogin(rs.getLong("login"));
                usuarioOBJ.setNomeUsuario(rs.getString("nome_usuario"));
                usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.valueOf(rs.getString("categoria_usuario")));
                usuarioOBJ.setEmail(rs.getString("email"));
                usuarioOBJ.setStatusUsuario(rs.getString("status_usuario"));
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
        return usuarioOBJ;
    }

    public Usuario autenticarUsuario(Usuario usuario) {

        Connection conexao = null;
        Usuario usuarioOBJ = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(AUTENTICA_USUARIO);

            pstmt.setLong(1, usuario.getLogin());
            pstmt.setString(2, usuario.getSenha());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuarioOBJ = new Usuario();
                usuarioOBJ.setIdUsuario(rs.getLong("id_usuario"));
                usuarioOBJ.setLogin(rs.getLong("login"));
                usuarioOBJ.setNomeUsuario(rs.getString("nome_usuario"));
                usuarioOBJ.setCategoriaUsuario(CategoriaUsuario.valueOf(rs.getString("categoria_usuario")));
                usuarioOBJ.setStatusUsuario(rs.getString("status_usuario"));
            }
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
        return usuarioOBJ;
    }

    public int retornarQuantidadePorCategoria(Usuario usuario) {
        Connection conexao = null;
        int quantidadeParentesco = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(QTDEPORCATEGORIA);

            pstmt.setString(1, usuario.getCategoriaUsuario().toString());

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

    public void alterar(Usuario usuario) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);

            pstmt.setString(1, usuario.getNomeUsuario());
            pstmt.setString(2, usuario.getCategoriaUsuario().toString());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setLong(4, usuario.getIdUsuario());

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

    public void alterarLogin(Usuario usuario) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARLOGIN);

            pstmt.setLong(1, usuario.getLogin());
            pstmt.setLong(2, usuario.getIdUsuario());

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

    public void alterarSenhaStatus(Usuario usuario) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSENHASTATUS);

            pstmt.setString(1, usuario.getSenha());
            pstmt.setString(2, usuario.getStatusUsuario());
            pstmt.setLong(3, usuario.getIdUsuario());

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

    public void alterarStatus(Usuario usuario) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUS);

            pstmt.setString(1, usuario.getStatusUsuario());
            pstmt.setLong(2, usuario.getIdUsuario());

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
