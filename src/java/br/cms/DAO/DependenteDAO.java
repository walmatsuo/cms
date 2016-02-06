package br.cms.DAO;

import br.cms.Model.Dependente;
import br.cms.Model.Parentesco;
import br.cms.Model.Socio;
import br.cms.BD.ConectaBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DependenteDAO {

    private static final String CADASTRAR =                 "INSERT INTO dependente "
                                                            + "(nome_dependente,nascimento_dependente,sexo_dependente,nacionalidade_dependente,naturalidade_dependente,estado_civil_dependente,status_dependente,parentesco,socio) "
                                                            + "VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String LISTARTODOSPORSOCIO =       "SELECT * FROM dependente WHERE socio=? AND status_dependente IN ('a','b','d') ORDER BY nome_dependente";
    private static final String LISTARTODOSPORSTATUS =      "SELECT * FROM dependente WHERE status_dependente=? ORDER BY nome_dependente";
    private static final String PESQUISAR =                 "SELECT * FROM dependente WHERE socio=? AND status_dependente IN ('a','b','d') AND nome_dependente ILIKE ? ORDER BY nome_dependente";
    private static final String BUSCARPORID =               "SELECT * FROM dependente WHERE id_dependente=? AND status_dependente IN ('a','b','d')";
    private static final String QTDEPORSOCIO =              "SELECT COUNT (socio) FROM dependente WHERE socio=? AND status_dependente IN ('a')";
    private static final String QTDEPORPARENTESCO =         "SELECT COUNT(*) FROM dependente WHERE status_dependente IN ('a','b','d') AND parentesco=?";
    private static final String ATUALIZAR =                 "UPDATE dependente "
                                                            + "SET (nome_dependente,nascimento_dependente,sexo_dependente,nacionalidade_dependente,naturalidade_dependente,estado_civil_dependente,parentesco) = "
                                                            + "(?,?,?,?,?,?,?) WHERE id_dependente=?";
    private static final String ATUALIZARSTATUS =           "UPDATE dependente SET status_dependente=? WHERE id_dependente=?";
    private static final String ATUALIZARSTATUSPORSOCIO =   "UPDATE dependente SET status_dependente=? WHERE socio=?";

    public void cadastrar(Dependente dependente) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR);

            pstmt.setString(1, dependente.getNomeDependente());
            pstmt.setDate(2, new Date(dependente.getNascimentoDependente().getTime()));
            pstmt.setString(3, dependente.getSexoDependente());
            pstmt.setString(4, dependente.getNacionalidadeDependente());
            pstmt.setString(5, dependente.getNaturalidadeDependente());
            pstmt.setString(6, dependente.getEstadoCivilDependente());
            pstmt.setString(7, dependente.getStatusDependente());
            pstmt.setInt(8, dependente.getParentescoDependente().getIdParentesco());
            pstmt.setLong(9, dependente.getSocioDependente().getIdSocio());

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

    public ArrayList<Dependente> listarTodosPorSocio(Dependente dependente) {

        Connection conexao = null;
        ArrayList<Dependente> listaDependente = new ArrayList<Dependente>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARTODOSPORSOCIO);

            pstmt.setLong(1, dependente.getSocioDependente().getIdSocio());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Dependente dependenteOBJ = new Dependente();
                dependenteOBJ.setIdDependente(rs.getLong("id_dependente"));
                dependenteOBJ.setNomeDependente(rs.getString("nome_dependente"));
                dependenteOBJ.setNascimentoDependente(rs.getDate("nascimento_dependente"));
                dependenteOBJ.setNacionalidadeDependente(rs.getString("nacionalidade_dependente"));
                dependenteOBJ.setSexoDependente(rs.getString("sexo_dependente"));
                dependenteOBJ.setNaturalidadeDependente(rs.getString("naturalidade_dependente"));
                dependenteOBJ.setEstadoCivilDependente(rs.getString("estado_civil_dependente"));
                dependenteOBJ.setStatusDependente(rs.getString("status_dependente"));

                Parentesco parentescoOBJ = new Parentesco();
                parentescoOBJ.setIdParentesco(rs.getInt("parentesco"));

                dependenteOBJ.setParentescoDependente(parentescoOBJ);

                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("socio"));

                dependenteOBJ.setSocioDependente(socioOBJ);

                listaDependente.add(dependenteOBJ);
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
        return listaDependente;
    }

    public ArrayList<Dependente> listarTodosPorStatus(Dependente dependente) {

        Connection conexao = null;
        ArrayList<Dependente> listaDependente = new ArrayList<Dependente>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARTODOSPORSTATUS);

            pstmt.setString(1, dependente.getStatusDependente());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Dependente dependenteOBJ = new Dependente();
                dependenteOBJ.setIdDependente(rs.getLong("id_dependente"));
                dependenteOBJ.setNomeDependente(rs.getString("nome_dependente"));
                dependenteOBJ.setNascimentoDependente(rs.getDate("nascimento_dependente"));
                dependenteOBJ.setNacionalidadeDependente(rs.getString("nacionalidade_dependente"));
                dependenteOBJ.setSexoDependente(rs.getString("sexo_dependente"));
                dependenteOBJ.setNaturalidadeDependente(rs.getString("naturalidade_dependente"));
                dependenteOBJ.setEstadoCivilDependente(rs.getString("estado_civil_dependente"));
                dependenteOBJ.setStatusDependente(rs.getString("status_dependente"));

                Parentesco parentescoOBJ = new Parentesco();
                parentescoOBJ.setIdParentesco(rs.getInt("parentesco"));

                dependenteOBJ.setParentescoDependente(parentescoOBJ);

                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("socio"));

                dependenteOBJ.setSocioDependente(socioOBJ);

                listaDependente.add(dependenteOBJ);
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
        return listaDependente;
    }

    public ArrayList<Dependente> pesquisar(Dependente dependente) {

        Connection conexao = null;
        ArrayList<Dependente> listaDependente = new ArrayList<Dependente>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(PESQUISAR);

            pstmt.setLong(1, dependente.getSocioDependente().getIdSocio());
            pstmt.setString(2, "%" + dependente.getNomeDependente() + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Dependente dependenteOBJ = new Dependente();
                dependenteOBJ.setIdDependente(rs.getLong("id_dependente"));
                dependenteOBJ.setNomeDependente(rs.getString("nome_dependente"));
                dependenteOBJ.setNascimentoDependente(rs.getDate("nascimento_dependente"));
                dependenteOBJ.setSexoDependente(rs.getString("sexo_dependente"));
                dependenteOBJ.setNacionalidadeDependente(rs.getString("nacionalidade_dependente"));
                dependenteOBJ.setNaturalidadeDependente(rs.getString("naturalidade_dependente"));
                dependenteOBJ.setEstadoCivilDependente(rs.getString("estado_civil_dependente"));
                dependenteOBJ.setStatusDependente(rs.getString("status_dependente"));

                Parentesco parentescoOBJ = new Parentesco();
                parentescoOBJ.setIdParentesco(rs.getInt("parentesco"));

                dependenteOBJ.setParentescoDependente(parentescoOBJ);

                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("socio"));

                dependenteOBJ.setSocioDependente(socioOBJ);

                listaDependente.add(dependenteOBJ);
            }

            // Encerra o PreparedStatement
            pstmt.close();

            // Retorna listaCrud com as tuplas encontradas    
            return listaDependente;
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

    public Dependente consultarPorId(Dependente dependente) {

        Connection conexao = null;
        Dependente dependenteOBJ = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORID);

            pstmt.setLong(1, dependente.getIdDependente());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                dependenteOBJ = new Dependente();
                dependenteOBJ.setIdDependente(rs.getLong("id_dependente"));
                dependenteOBJ.setNomeDependente(rs.getString("nome_dependente"));
                dependenteOBJ.setNascimentoDependente(rs.getDate("nascimento_dependente"));
                dependenteOBJ.setSexoDependente(rs.getString("sexo_dependente"));
                dependenteOBJ.setNacionalidadeDependente(rs.getString("nacionalidade_dependente"));
                dependenteOBJ.setNaturalidadeDependente(rs.getString("naturalidade_dependente"));
                dependenteOBJ.setEstadoCivilDependente(rs.getString("estado_civil_dependente"));
                dependenteOBJ.setStatusDependente(rs.getString("status_dependente"));

                Parentesco parentescoOBJ = new Parentesco();
                parentescoOBJ.setIdParentesco(rs.getInt("parentesco"));

                dependenteOBJ.setParentescoDependente(parentescoOBJ);

                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("socio"));

                dependenteOBJ.setSocioDependente(socioOBJ);
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
        return dependenteOBJ;
    }

    public int retornarQuantidadePorSocio(Dependente dependente) {

        Connection conexao = null;
        int quantidadeDependente = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(QTDEPORSOCIO);

            pstmt.setLong(1, dependente.getSocioDependente().getIdSocio());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                quantidadeDependente = rs.getInt(1);
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
        return quantidadeDependente;
    }

    public int retornarQuantidadePorParentesco(Dependente dependente) {

        Connection conexao = null;
        int quantidadeDependente = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(QTDEPORPARENTESCO);

            pstmt.setLong(1, dependente.getParentescoDependente().getIdParentesco());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                quantidadeDependente = rs.getInt(1);
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
        return quantidadeDependente;
    }

    public void alterar(Dependente dependente) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);

            pstmt.setString(1, dependente.getNomeDependente());
            pstmt.setDate(2, new Date(dependente.getNascimentoDependente().getTime()));
            pstmt.setString(3, dependente.getSexoDependente());
            pstmt.setString(4, dependente.getNacionalidadeDependente());
            pstmt.setString(5, dependente.getNaturalidadeDependente());
            pstmt.setString(6, dependente.getEstadoCivilDependente());
            pstmt.setInt(7, dependente.getParentescoDependente().getIdParentesco());
            pstmt.setLong(8, dependente.getIdDependente());

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

    public void alterarStatus(Dependente dependente) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUS);

            pstmt.setString(1, dependente.getStatusDependente());
            pstmt.setLong(2, dependente.getIdDependente());

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

    public void alterarStatusPorSocio(Dependente dependente) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUSPORSOCIO);

            pstmt.setString(1, dependente.getStatusDependente());
            pstmt.setLong(2, dependente.getSocioDependente().getIdSocio());

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
