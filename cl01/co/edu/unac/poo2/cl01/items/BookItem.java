package co.edu.unac.poo2.cl01.items;

import java.util.Date;

public class BookItem {
    
    public String barCode;
    public Book book;
    public boolean isReference;
    public int numberOfPages;
    public Date loanedDate;
    public int loanPeriod;
    public Date dueDate;
    public boolean isOverdue;
}
