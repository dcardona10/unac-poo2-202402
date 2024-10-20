package dao;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    private static Connection connection;

    public UserDAO() {
        if (connection == null) {
            try {
                Class.forName(SQLConstants.SQL_DB_CLASS);
                connection = DriverManager.getConnection(SQLConstants.SQL_CONNECTION, SQLConstants.SQL_USER, SQLConstants.SQL_PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<User> searchUser(String keyword) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLConstants.SQL_GET_USERS);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                user.setType(resultSet.getString("type"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void createUser(User user) {

        try {
            PreparedStatement statement = connection.prepareStatement(SQLConstants.SQL_CREATE_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
