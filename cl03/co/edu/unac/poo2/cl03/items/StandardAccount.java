package co.edu.unac.poo2.cl03.items;

import java.util.Date;

public class StandardAccount extends Account {

    public StandardAccount(int accountId, User user, Date openedDate, int daysOfLoan) {
        super(accountId, user, openedDate, daysOfLoan);
    }

    @Override
    public String showAccountType() {
        return "StandardAccount";
    }

    @Override
    public String showDaysOfLoan() {
        return "Days of loan: " + getDaysOfLoan();
    }
}
