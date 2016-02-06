package br.cms.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/cm2";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static Connection conectarBD() {
        Connection conexao = null;

        try {
            //driver que será utilizado 
            Class.forName(DRIVER);
            //cria um objeto de conexao com um banco de dados 
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Sem conexão ao Banco de Dados, notifique o TI.");

        } catch (SQLException e) {
            throw new RuntimeException("Sem conexão ao Banco de Dados, notifique o TI.");
        }
        return conexao;
    }
}
