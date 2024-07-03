package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private  List<CheckoutEntry> entries = new ArrayList<>();
    private String memberId ;

    public CheckoutRecord(String memberId, CheckoutEntry entry) {
        entries.add(entry);
        this.memberId = memberId;
    }

    public void addEntry(CheckoutEntry entry) {
        entries.add(entry);
    }

    public  List<CheckoutEntry> getEntries() {
        return entries;
    }

    public String getMemberId(){
        return memberId;
    }

    public List<BookCopy> getCheckedOutCopies() {
        List<BookCopy> checkedOutCopies = new ArrayList<>();
        for (CheckoutEntry entry : entries) {
            checkedOutCopies.add(entry.getBookCopy());
        }
        return checkedOutCopies;
    }
}

