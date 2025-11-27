package academy.devdojo.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection () {
        String url = "jdbc:mysql://localhost:3307/user_db";
        String username = "root";
        String password = "root";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
