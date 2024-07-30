package co.edu.unac.poo2.cl01;

import java.time.LocalDate;
import java.util.Date;

import co.edu.unac.poo2.cl01.config.AccountState;
import co.edu.unac.poo2.cl01.items.*;

public class Main {    
    
    public static void main(String[] args) {

        User user = new User();
        user.userId = 252;
        user.name = "David";

        AccountState state = new AccountState();
        state.stateId = 1;
        state.stateName = "Enabled";

        Account account = new Account();
        account.accountId = 447;
        account.user = user;
        account.openedDate = new Date();
        account.state = state;

        Author author = new Author();
        author.authorId = 448;
        author.name = "Stephen King";

        Book book = new Book();
        book.isbn = "97859033774";
        book.title = "The Shining";
        book.author = author;
        book.itemsAvailable = 3;

        BookItem bookItem = new BookItem();
        bookItem.barCode = "567938894";
        bookItem.book = book;

        BookItemLoan loan = new BookItemLoan();
        loan.loanId = "67895045";
        loan.bookItem = bookItem;
        loan.account = account;

        loan.loan(10);
        System.out.println("Loan Date: " + loan.bookItem.loanedDate);
        System.out.println("Due Date: " + loan.bookItem.dueDate);
        System.out.println("Items Available : " + loan.bookItem.book.itemsAvailable);
    }
}
