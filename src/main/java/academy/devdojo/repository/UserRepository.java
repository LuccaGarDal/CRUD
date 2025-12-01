package academy.devdojo.repository;

import academy.devdojo.conn.ConnectionFactory;
import academy.devdojo.domain.User;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class UserRepository {

    public static void save (User user) {
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = savePreparedStatement(connection, user)) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while trying to insert user");
        }
        log.info("User inserted on database");

    }

    private static PreparedStatement savePreparedStatement (Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO `user_db`.`user` (`name`, `age`) VALUES (?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setInt(2, user.getAge());
        return ps;
    }
}
