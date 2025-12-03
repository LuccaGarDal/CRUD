package academy.devdojo.repository;

import academy.devdojo.conn.ConnectionFactory;
import academy.devdojo.domain.User;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<User> findByName (String name) {
        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = findByNamePreparedStatement(name, connection);
        ResultSet rs = ps.executeQuery();) {
            List<User> users = new ArrayList<>();

            while (rs.next()) {
                User user = User.builder()
                        .name(rs.getString("name"))
                        .age(rs.getInt("age"))
                        .id(rs.getInt("id"))
                        .build();
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement findByNamePreparedStatement (String name, Connection conn) throws SQLException {
        String sql = "SELECT * FROM user_db.user WHERE name LIKE ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%%%s%%".formatted(name));
        return ps;
    }

    public static User findById (int id) {
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = findByIdPreparedStatement(id, conn);
        ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                return User.builder()
                        .name(rs.getString("name"))
                        .age(rs.getInt("age"))
                        .id(rs.getInt("id"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static PreparedStatement findByIdPreparedStatement (int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM user_db.user WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static void delete (int id) {
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = deletePreparedStatement(id, conn)) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement deletePreparedStatement(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM user_db.user WHERE id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static void update (User user) {
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = updatePreparedStatement(conn, user)) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement updatePreparedStatement (Connection conn, User user) throws SQLException {
        String sql = "UPDATE user_db.user SET name = ?, age = ? WHERE (id = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setInt(2, user.getAge());
        ps.setInt(3, user.getId());
        return ps;

    }


}
