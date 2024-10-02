public class SQLConstants {

    public static final String SQL_CONNECTION = "jdbc:mysql://localhost:3306/unac?serverTimezone=UTC";
    public static final String SQL_USER = "root";
    public static final String SQL_PASSWORD = "Mysql123#";

    public static final String SQL_GET_USER_BY_CREDENTIALS = "SELECT * FROM user WHERE username = ? AND password = ?";

}
