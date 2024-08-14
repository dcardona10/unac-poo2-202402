package co.edu.unac.poo2.cl02.items;

import java.util.Calendar;
import java.util.Date;

public class BookItemLoan {
    
    public String loanId;
    public BookItem bookItem;
    public Account account;

    public void loan(int period) {
        if (bookItem.book.itemsAvailable > 0) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            bookItem.loanedDate = cal.getTime();
            bookItem.loanPeriod = period;
            cal.add(Calendar.DATE, period);
            bookItem.dueDate = cal.getTime();
            bookItem.book.itemsAvailable -= 1;
            System.out.println("Book " + bookItem.book.title + " loaned successfully");
        } else {
            System.out.println("User cannot loan book and/or there are not available items for this title");
        }
    }
}
