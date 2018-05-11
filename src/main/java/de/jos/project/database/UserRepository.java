package de.jos.project.database;

import de.jos.project.model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.function.Supplier;

@Component
public class UserRepository {
    private final String DB_URL = "jdbc:h2:~/test";
    private final String USER = "sa";
    private final String PASS = "";
    private Statement statement;
    private Connection connection;


    public void insertNewUser(User user) {
        wrapStatement(() -> insertUserInternal(user));
    }

    public User findUserByID(String id) {
        return wrapQuery(() -> findUserByIDInternal(id));
    }

    private User findUserByIDInternal(String id) {
        String query = "SELECT * FROM User WHERE ID=" + id;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            return getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }

    private void insertUserInternal(User user) {
        String sql = "INSERT INTO User " + "VALUES " + user.toSQLString();
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }

    private <T> T wrapQuery(Supplier<T> supplier) {
        try {
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.createStatement();

            T result = supplier.get();

            connection.close();
            statement.close();

            return result;
        } catch (SQLException e) {
            return null;
        }
    }

    private void wrapStatement(Runnable runnable) {
        try {
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.createStatement();

            runnable.run();

            connection.close();
            statement.close();
        } catch (SQLException e) {
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException{
        if (!resultSet.next()) {
            return null;
        } else {
            return new User(resultSet.getString("id"),
                    resultSet.getString("api_Token"),
                    resultSet.getString("projectID"),
                    resultSet.getString("serviceID"));
        }
    }

}
