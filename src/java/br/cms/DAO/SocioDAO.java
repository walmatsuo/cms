package br.cms.DAO;

import br.cms.BD.ConectaBanco;
import br.cms.Model.CategoriaSocio;
import br.cms.Model.Socio;
import br.cms.Model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SocioDAO {

    private static final String CADASTRAR =         "INSERT INTO socio "
                                                    + "(nome_socio,cpf_socio,logradouro,numero,complemento,bairro,cep,municipio,uf,nascimento_socio,sexo_socio,nacionalidade_socio,naturalidade_socio,estado_civil_socio,profissao,telefone1,telefone2,telefone3,inclusao_socio,status_socio,categoria_socio,usuario) "
                                                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String LISTARTODOS =       "SELECT * FROM socio WHERE status_socio IN ('a','b') ORDER BY nome_socio";
    private static final String LISTARPORSTATUS =   "SELECT * FROM socio WHERE status_socio=? ORDER BY nome_socio";
    private static final String PESQUISAR =         "SELECT * FROM socio WHERE status_socio IN ('a','b') AND nome_socio ILIKE ? ORDER BY nome_socio";
    private static final String BUSCARPORID =       "SELECT * FROM socio WHERE status_socio IN ('a','b') and id_socio=?";
    private static final String QTDEPORCATEGORIA =  "SELECT COUNT(id_socio) FROM socio WHERE status_socio IN ('a','b') AND categoria_socio=?";
    private static final String QTDEPORSTATUS =     "SELECT COUNT(id_socio) FROM socio WHERE status_socio=?";
    private static final String ATUALIZAR =         "UPDATE socio "
                                                    + "SET (nome_socio,logradouro,numero,complemento,bairro,cep,municipio,uf,nascimento_socio,sexo_socio,nacionalidade_socio,naturalidade_socio,estado_civil_socio,profissao,telefone1,telefone2,telefone3,categoria_socio) = "
                                                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) WHERE id_socio=?";
    private static final String ATUALIZARSTATUS =   "UPDATE socio SET status_socio=? WHERE id_socio=?";

    public long cadastrar(Socio socio) {

        Connection conexao = null;
        long returnid = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(CADASTRAR, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, socio.getNomeSocio());
            pstmt.setString(2, socio.getCpfSocio());
            pstmt.setString(3, socio.getLogradouro());
            pstmt.setString(4, socio.getNumero());
            pstmt.setString(5, socio.getComplemento());
            pstmt.setString(6, socio.getBairro());
            pstmt.setString(7, socio.getCep());
            pstmt.setString(8, socio.getMunicipio());
            pstmt.setString(9, socio.getUf());
            pstmt.setDate(10, new Date(socio.getNascimentoSocio().getTime()));
            pstmt.setString(11, socio.getSexoSocio());
            pstmt.setString(12, socio.getNacionalidadeSocio());
            pstmt.setString(13, socio.getNaturalidadeSocio());
            pstmt.setString(14, socio.getEstadoCivilSocio());
            pstmt.setString(15, socio.getProfissao());
            pstmt.setString(16, socio.getTelefone1());
            pstmt.setString(17, socio.getTelefone2());
            pstmt.setString(18, socio.getTelefone3());
            pstmt.setDate(19, new Date(socio.getInclusaoSocio().getTime()));
            pstmt.setString(20, socio.getStatusSocio());
            pstmt.setLong(21, socio.getCategoriaSocio().getIdCategoriaSocio());
            pstmt.setLong(22, socio.getUsuarioSocio().getIdUsuario());

            pstmt.execute();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                returnid = rs.getLong("id_socio");
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

    public ArrayList<Socio> listarTodos() {

        Connection conexao = null;
        ArrayList<Socio> listaSocio = new ArrayList<Socio>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARTODOS);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("id_socio"));
                socioOBJ.setNomeSocio(rs.getString("nome_socio"));
                socioOBJ.setCpfSocio(rs.getString("cpf_socio"));
                socioOBJ.setLogradouro(rs.getString("logradouro"));
                socioOBJ.setNumero(rs.getString("numero"));
                socioOBJ.setComplemento(rs.getString("complemento"));
                socioOBJ.setBairro(rs.getString("bairro"));
                socioOBJ.setCep(rs.getString("cep"));
                socioOBJ.setMunicipio(rs.getString("municipio"));
                socioOBJ.setUf(rs.getString("uf"));
                socioOBJ.setNascimentoSocio(rs.getDate("nascimento_socio"));
                socioOBJ.setSexoSocio(rs.getString("sexo_socio"));
                socioOBJ.setNacionalidadeSocio(rs.getString("nacionalidade_socio"));
                socioOBJ.setNaturalidadeSocio(rs.getString("naturalidade_socio"));
                socioOBJ.setEstadoCivilSocio(rs.getString("estado_civil_socio"));
                socioOBJ.setProfissao(rs.getString("profissao"));
                socioOBJ.setTelefone1(rs.getString("telefone1"));
                socioOBJ.setTelefone2(rs.getString("telefone2"));
                socioOBJ.setTelefone3(rs.getString("telefone3"));
                socioOBJ.setInclusaoSocio(rs.getDate("inclusao_socio"));
                socioOBJ.setStatusSocio(rs.getString("status_socio"));

                CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
                categoriaSocioOBJ.setIdCategoriaSocio(rs.getLong("categoria_socio"));

                Usuario usuarioOBJ = new Usuario();
                usuarioOBJ.setIdUsuario(rs.getLong("usuario"));

                socioOBJ.setCategoriaSocio(categoriaSocioOBJ);
                socioOBJ.setUsuarioSocio(usuarioOBJ);

                listaSocio.add(socioOBJ);
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
        return listaSocio;
    }

    public ArrayList<Socio> listarPorStatus(Socio socio) {

        Connection conexao = null;
        ArrayList<Socio> listaSocio = new ArrayList<Socio>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(LISTARPORSTATUS);

            pstmt.setString(1, socio.getStatusSocio());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("id_socio"));
                socioOBJ.setNomeSocio(rs.getString("nome_socio"));
                socioOBJ.setCpfSocio(rs.getString("cpf_socio"));
                socioOBJ.setLogradouro(rs.getString("logradouro"));
                socioOBJ.setNumero(rs.getString("numero"));
                socioOBJ.setComplemento(rs.getString("complemento"));
                socioOBJ.setBairro(rs.getString("bairro"));
                socioOBJ.setCep(rs.getString("cep"));
                socioOBJ.setMunicipio(rs.getString("municipio"));
                socioOBJ.setUf(rs.getString("uf"));
                socioOBJ.setNascimentoSocio(rs.getDate("nascimento_socio"));
                socioOBJ.setSexoSocio(rs.getString("sexo_socio"));
                socioOBJ.setNacionalidadeSocio(rs.getString("nacionalidade_socio"));
                socioOBJ.setNaturalidadeSocio(rs.getString("naturalidade_socio"));
                socioOBJ.setEstadoCivilSocio(rs.getString("estado_civil_socio"));
                socioOBJ.setProfissao(rs.getString("profissao"));
                socioOBJ.setTelefone1(rs.getString("telefone1"));
                socioOBJ.setTelefone2(rs.getString("telefone2"));
                socioOBJ.setTelefone3(rs.getString("telefone3"));
                socioOBJ.setInclusaoSocio(rs.getDate("inclusao_socio"));
                socioOBJ.setStatusSocio(rs.getString("status_socio"));

                CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
                categoriaSocioOBJ.setIdCategoriaSocio(rs.getLong("categoria_socio"));

                Usuario usuarioOBJ = new Usuario();
                usuarioOBJ.setIdUsuario(rs.getLong("usuario"));

                socioOBJ.setCategoriaSocio(categoriaSocioOBJ);
                socioOBJ.setUsuarioSocio(usuarioOBJ);

                listaSocio.add(socioOBJ);
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
        return listaSocio;
    }

    public ArrayList<Socio> pesquisar(Socio socio) {

        Connection conexao = null;
        ArrayList<Socio> listaSocio = new ArrayList<Socio>();

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(PESQUISAR);

            pstmt.setString(1, "%" + socio.getNomeSocio() + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Socio socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("id_socio"));
                socioOBJ.setNomeSocio(rs.getString("nome_socio"));
                socioOBJ.setCpfSocio(rs.getString("cpf_socio"));
                socioOBJ.setLogradouro(rs.getString("logradouro"));
                socioOBJ.setNumero(rs.getString("numero"));
                socioOBJ.setComplemento(rs.getString("complemento"));
                socioOBJ.setBairro(rs.getString("bairro"));
                socioOBJ.setCep(rs.getString("cep"));
                socioOBJ.setMunicipio(rs.getString("municipio"));
                socioOBJ.setUf(rs.getString("uf"));
                socioOBJ.setNascimentoSocio(rs.getDate("nascimento_socio"));
                socioOBJ.setSexoSocio(rs.getString("sexo_socio"));
                socioOBJ.setNacionalidadeSocio(rs.getString("nacionalidade_socio"));
                socioOBJ.setNaturalidadeSocio(rs.getString("naturalidade_socio"));
                socioOBJ.setEstadoCivilSocio(rs.getString("estado_civil_socio"));
                socioOBJ.setProfissao(rs.getString("profissao"));
                socioOBJ.setTelefone1(rs.getString("telefone1"));
                socioOBJ.setTelefone2(rs.getString("telefone2"));
                socioOBJ.setTelefone3(rs.getString("telefone3"));
                socioOBJ.setInclusaoSocio(rs.getDate("inclusao_socio"));
                socioOBJ.setStatusSocio(rs.getString("status_socio"));

                CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
                categoriaSocioOBJ.setIdCategoriaSocio(rs.getLong("categoria_socio"));

                Usuario usuarioOBJ = new Usuario();
                usuarioOBJ.setIdUsuario(rs.getLong("usuario"));

                socioOBJ.setCategoriaSocio(categoriaSocioOBJ);
                socioOBJ.setUsuarioSocio(usuarioOBJ);

                listaSocio.add(socioOBJ);
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
        return listaSocio;
    }

    public Socio consultarPorId(Socio socio) {

        Connection conexao = null;
        Socio socioOBJ = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(BUSCARPORID);

            pstmt.setLong(1, socio.getIdSocio());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                socioOBJ = new Socio();
                socioOBJ.setIdSocio(rs.getLong("id_socio"));
                socioOBJ.setNomeSocio(rs.getString("nome_socio"));
                socioOBJ.setCpfSocio(rs.getString("cpf_socio"));
                socioOBJ.setLogradouro(rs.getString("logradouro"));
                socioOBJ.setNumero(rs.getString("numero"));
                socioOBJ.setComplemento(rs.getString("complemento"));
                socioOBJ.setBairro(rs.getString("bairro"));
                socioOBJ.setCep(rs.getString("cep"));
                socioOBJ.setMunicipio(rs.getString("municipio"));
                socioOBJ.setUf(rs.getString("uf"));
                socioOBJ.setNascimentoSocio(rs.getDate("nascimento_socio"));
                socioOBJ.setSexoSocio(rs.getString("sexo_socio"));
                socioOBJ.setNacionalidadeSocio(rs.getString("nacionalidade_socio"));
                socioOBJ.setNaturalidadeSocio(rs.getString("naturalidade_socio"));
                socioOBJ.setEstadoCivilSocio(rs.getString("estado_civil_socio"));
                socioOBJ.setProfissao(rs.getString("profissao"));
                socioOBJ.setTelefone1(rs.getString("telefone1"));
                socioOBJ.setTelefone2(rs.getString("telefone2"));
                socioOBJ.setTelefone3(rs.getString("telefone3"));
                socioOBJ.setInclusaoSocio(rs.getDate("inclusao_socio"));
                socioOBJ.setStatusSocio(rs.getString("status_socio"));

                CategoriaSocio categoriaSocioOBJ = new CategoriaSocio();
                categoriaSocioOBJ.setIdCategoriaSocio(rs.getLong("categoria_socio"));

                Usuario usuarioOBJ = new Usuario();
                usuarioOBJ.setIdUsuario(rs.getLong("usuario"));

                socioOBJ.setCategoriaSocio(categoriaSocioOBJ);
                socioOBJ.setUsuarioSocio(usuarioOBJ);
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
        return socioOBJ;
    }

    public int retornarQuantidadePorCategoria(Socio socio) {

        Connection conexao = null;
        int quantidadeSocio = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(QTDEPORCATEGORIA);

            pstmt.setLong(1, socio.getCategoriaSocio().getIdCategoriaSocio());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                quantidadeSocio = rs.getInt(1);
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
        return quantidadeSocio;
    }

    public int retornarQuantidadePorStatus(Socio socio) {

        Connection conexao = null;
        int quantidadeSocio = 0;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(QTDEPORSTATUS);

            pstmt.setString(1, socio.getStatusSocio());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                quantidadeSocio = rs.getInt(1);
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
        return quantidadeSocio;
    }

    public void alterar(Socio socio) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZAR);

            pstmt.setString(1, socio.getNomeSocio());
            pstmt.setString(2, socio.getLogradouro());
            pstmt.setString(3, socio.getNumero());
            pstmt.setString(4, socio.getComplemento());
            pstmt.setString(5, socio.getBairro());
            pstmt.setString(6, socio.getCep());
            pstmt.setString(7, socio.getMunicipio());
            pstmt.setString(8, socio.getUf());
            pstmt.setDate(9, new Date(socio.getNascimentoSocio().getTime()));
            pstmt.setString(10, socio.getSexoSocio());
            pstmt.setString(11, socio.getNacionalidadeSocio());
            pstmt.setString(12, socio.getNaturalidadeSocio());
            pstmt.setString(13, socio.getEstadoCivilSocio());
            pstmt.setString(14, socio.getProfissao());
            pstmt.setString(15, socio.getTelefone1());
            pstmt.setString(16, socio.getTelefone2());
            pstmt.setString(17, socio.getTelefone3());
            pstmt.setLong(18, socio.getCategoriaSocio().getIdCategoriaSocio());
            pstmt.setLong(19, socio.getIdSocio());

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

    public void alterarStatus(Socio socio) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.conectarBD();

            PreparedStatement pstmt = conexao.prepareStatement(ATUALIZARSTATUS);

            pstmt.setString(1, socio.getStatusSocio());
            pstmt.setLong(2, socio.getIdSocio());

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
