package greenatom.projects.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "gnusmas2013";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test";
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
