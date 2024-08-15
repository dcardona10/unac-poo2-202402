package co.edu.unac.poo2.cl03.items;

import java.util.Date;

public class PremiumAccount extends Account {

    private boolean extendLoan;

    public PremiumAccount(int accountId, User user, Date openedDate, int daysOfLoan) {
        super(accountId, user, openedDate, daysOfLoan);
    }

    @Override
    public String showAccountType() {
        return "Premium Account";
    }

    @Override
    public String showDaysOfLoan() {
        if (!isExtendLoan()) {
            return "Days of loan: " + getDaysOfLoan();
        } else {
            return "Days of loan: " + (getDaysOfLoan() + 10);
        }
    }

    public boolean isExtendLoan() {
        return extendLoan;
    }

    public void setExtendLoan(boolean extendLoan) {
        this.extendLoan = extendLoan;
    }
}
