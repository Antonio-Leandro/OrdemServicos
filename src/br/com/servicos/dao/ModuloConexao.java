package br.com.servicos.dao;

import java.sql.*;

public interface ModuloConexao {

    public static Connection conector(){
        java.sql.Connection conexao = null;
        String driver = "com.mysql.jdbc.Driver";
        //String url = "jdbc:mysql://localhost:3306/servicos";
        //String usuario ="root";
        //String senha ="Alean045";
        String url = "jdbc:mysql://localhost:3306/servicos?characterEncoding=utf-8";
        String usuario ="dba";
        String senha ="alean045";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            return conexao;
        } catch (Exception e) {
            System.out.println("Erro de conexão com o banco de dados..." + e.getMessage());
            return null;
        }
    }
}
