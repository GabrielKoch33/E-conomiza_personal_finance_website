package com.gabriel.prototype.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {

    public static Connection getConnection() {
        String url      = "jdbc:postgresql://localhost:5432/economiza";
        String user     = "postgres";
        String password = "250107gks";

        try  {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


