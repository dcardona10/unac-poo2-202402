import java.util.Random;

public class SQLConstants {

    public static final String SQL_CONNECTION = "jdbc:mysql://localhost:3306/unac?serverTimezone=UTC";
    public static final String SQL_USER = "root";
    public static final String SQL_PASSWORD = "Mysql123#";

    public static final String SQL_GET_USER_BY_CREDENTIALS = "SELECT * FROM user WHERE username = ? AND password = ?";
    public static final String SQL_GET_USER_BY_USERNAME = "SELECT COUNT(*) as usercount FROM user WHERE username = ?";
    public static final String SQL_GET_USERS = "SELECT first_name, last_name, email, username, type FROM user WHERE type <> 'Admin'";

    public static final String SQL_CREATE_USER = "INSERT INTO user (first_name, last_name, email, username, password, type) VALUES (?, ?, ?, ?, ?, ?)";

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
