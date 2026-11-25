package com.gabriel.prototype.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.gabriel.prototype.database.DBConnectionFactory;
import com.gabriel.prototype.exceptions.DataAccessException;
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
        String sql = "INSERT INTO tb_usuario (nome, senha, username) VALUES (?,?,?)";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql); )
        {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenhaHash());
            ps.setString(3, usuario.getUsername());

            int rows = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Optional<Usuario> edit(Usuario usuario) throws DataAccessException {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> deleteById(Long id) throws DataAccessException {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> findById(Long id) throws DataAccessException {
        return Optional.empty();
    }

    @Override
    public List<Usuario> listAll() throws DataAccessException {
        return List.of();
    }

    @Override
    public Optional<Usuario> findByUsername(String username) throws DataAccessException {
        return Optional.empty();
    }
}
