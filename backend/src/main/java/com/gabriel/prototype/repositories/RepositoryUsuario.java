package com.gabriel.prototype.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.gabriel.prototype.database.DBConnectionFactory;
import com.gabriel.prototype.models.Usuario;

/**
 * executeQuery() → SELECT, devolve ResultSet
 * executeUpdate() → INSERT/UPDATE/DELETE/DDL, devolve int com linhas afetadas
 * execute() → quando não se sabe o tipo, devolve boolean
 * executeBatch() → executa várias operações de uma vez
 * **/
public class RepositoryUsuario {

    public static void save (Usuario usuario) {
        String sql = "INSERT INTO tb_usuario (nome, senha, username) VALUES (?,?,?)";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql); )
        {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getUsername());
            // setDouble, setInt, setTimestamp, setBoolean...
            // protege contra SQL Injection

            int rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
