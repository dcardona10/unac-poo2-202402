package co.edu.unac.poo2.cl03.items;

import java.util.Date;

public abstract class Account {
    
    private int accountId;
    private User user;
    private Date openedDate;
    private int daysOfLoan;

    public Account(int accountId, User user, Date openedDate, int daysOfLoan) {
        this.accountId = accountId;
        this.user = user;
        this.openedDate = openedDate;
        this.daysOfLoan = daysOfLoan;
    }

    public abstract String showAccountType();
    public abstract String showDaysOfLoan();

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", user=" + user.toString() + ", openedDate=" + openedDate + ", daysOfLoan=" + daysOfLoan + "]";
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(Date openedDate) {
        this.openedDate = openedDate;
    }

    public int getDaysOfLoan() {
        return daysOfLoan;
    }

    public void setDaysOfLoan(int daysOfLoan) {
        this.daysOfLoan = daysOfLoan;
    }    
}
