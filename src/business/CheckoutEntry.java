package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private String memberId;

    public CheckoutEntry(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate) {
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

}