package com.gabriel.prototype.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.gabriel.prototype.database.DBConnectionFactory;
import com.gabriel.prototype.exceptions.DataAccessException;
import com.gabriel.prototype.models.Cofrinho;
import com.gabriel.prototype.models.Usuario;
import com.gabriel.prototype.repositories.interfaces.IUsuarioRepository;

/**
 * executeQuery() → SELECT, devolve ResultSet
 * executeUpdate() → INSERT/UPDATE/DELETE/DDL, devolve int com linhas afetadas
 * execute() → quando não se sabe o tipo, devolve boolean
 * executeBatch() → executa várias operações de uma vez
 * **/
public class UsuarioRepository implements IUsuarioRepository {


    @Override
    public void save(Usuario usuario) throws DataAccessException {
        String sql = "INSERT INTO tb_usuario (nome, senha, username)"+
                         " VALUES (?,?,?)";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql); )
        {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenhaHash());
            ps.setString(3, usuario.getUsername());
            // executeUpdate serve para INSERT, UPDATE e DELETE
            // use executeQuery para SELECT (combine com ResultSet)
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public int edit(Long id) throws DataAccessException {
        String sql = "UPDATE tb_usuario" +
                     "   SET ";
        return 0;
    }

    @Override
    public int deleteById(Long id) throws DataAccessException {
        return 1;
    }

    @Override
    public Optional<Usuario> findById(Long id) throws DataAccessException {
        return Optional.empty();
    }

    @Override
    public List<Usuario> listAll() throws DataAccessException {
        String sql = "SELECT ";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {

        }
        catch (SQLException e) {
            throw new DataAccessException(e);
        }
        return List.of();
    }

    @Override
    public Optional<Usuario> findByUsername(String username) throws DataAccessException {
        String sql  = "SELECT id_usuario, nome, username, senha" +
                      "  FROM tb_usuarios"+
                      " WHERE username = (?)";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(
                            rs.getLong("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("username"),
                            rs.getString("senha")
                    );
                    // estudar sobre
                    return Optional.of(usuario);
                }

            }
        }
        catch (SQLException e) {
            throw new DataAccessException(e);
        }
        // estudar sobre
        return Optional.empty();
    }
}
