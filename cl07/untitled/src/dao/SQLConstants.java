package dao;

import javax.swing.*;
import java.sql.*;
import java.util.Random;

public class SQLConstants {

    public static final String SQL_CONNECTION = "jdbc:mysql://localhost:3306/unac?serverTimezone=UTC";
    public static final String SQL_USER = "root";
    public static final String SQL_PASSWORD = "Mysql123#";
    public static final String SQL_DB_CLASS = "com.mysql.jdbc.Driver";

    public static final String SQL_GET_USER_BY_CREDENTIALS = "SELECT * FROM user WHERE username = ? AND password = ?";
    public static final String SQL_GET_USER_BY_USERNAME = "SELECT COUNT(*) as usercount FROM user WHERE username = ?";
    public static final String SQL_GET_USERS = "SELECT first_name, last_name, email, username, password, type FROM user WHERE type <> 'Admin' AND (first_name LIKE ? OR last_name LIKE ?)";

    public static final String SQL_CREATE_USER = "INSERT INTO user (first_name, last_name, email, username, password, type) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SQL_EDIT_USER = "UPDATE user SET first_name = ?, last_name = ?, email = ?, password = ?, type = ? WHERE username = ?";

    public static String createUsername(String firstName, String lastName) throws SQLException {
        String username = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
        try {
            Connection connection = DriverManager.getConnection(SQLConstants.SQL_CONNECTION, SQLConstants.SQL_USER, SQLConstants.SQL_PASSWORD);
            PreparedStatement queryUsernameStatement = connection.prepareStatement(SQLConstants.SQL_GET_USER_BY_USERNAME);
            queryUsernameStatement.setString(1, username);
            ResultSet resultSet = queryUsernameStatement.executeQuery();
            if (resultSet.getInt("usercount") >= 1) {
                username += (resultSet.getInt("usercount") + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username;
    }

    public static String createPassword() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 12;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

}
