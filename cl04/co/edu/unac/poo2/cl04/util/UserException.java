package co.edu.unac.poo2.cl04.util;

public class UserException extends Exception {

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message, null, false, true);
    }
}
