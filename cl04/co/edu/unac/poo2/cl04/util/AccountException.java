package co.edu.unac.poo2.cl04.util;

public class AccountException extends Exception {

    public AccountException() {
        super();
    }
    
    public AccountException(String message) {
        super(message, null, false, true);
    }

    public AccountException(String message, UserException e) {
        super(message, e, false, true);
    }
}
