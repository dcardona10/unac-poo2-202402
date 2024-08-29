package co.edu.unac.poo2.cl04.items;

public class Account {

    private String accountId;
    private User user;
    private char status;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", user=" + user.toString() + ", status=" + status + "]";
    }
}