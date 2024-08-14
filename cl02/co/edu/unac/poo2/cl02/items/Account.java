package co.edu.unac.poo2.cl02.items;

import java.util.Date;

public class Account {
    
    public int accountId;
    public User user;
    public Date openedDate;

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", user=" + user.toString() + ", openedDate=" + openedDate + "]";
    }
}
