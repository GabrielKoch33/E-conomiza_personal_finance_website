package com.gabriel.prototype.repositories;

import java.sql.Connection;
import com.gabriel.prototype.models.Usuario;

public class RepositoryUsuario {

    public static void saveUsuario (Connection conn, Usuario usuario) {
        String sql = "INSERT INTO tb_usuario (nome, senha, username) VALUES (?,?,?)";
    }
}
