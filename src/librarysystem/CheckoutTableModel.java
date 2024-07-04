package librarysystem;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import business.CheckoutEntry;

public class CheckoutTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Book ISBN", "Title", "Checkout Date", "Due Date"};
    private List<CheckoutEntry> checkoutEntries;

    public CheckoutTableModel(List<CheckoutEntry> checkoutEntries) {
        this.checkoutEntries = checkoutEntries;
    }

    @Override
    public int getRowCount() {
        return checkoutEntries.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CheckoutEntry entry = checkoutEntries.get(rowIndex);
        switch (columnIndex) {
            case 0: return entry.getBookCopy().getBook().getIsbn();
            case 1: return entry.getBookCopy().getBook().getTitle();
            case 2: return entry.getCheckoutDate();
            case 3: return entry.getDueDate();
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setCheckoutEntries(List<CheckoutEntry> checkoutEntries) {
        this.checkoutEntries = checkoutEntries;
        fireTableDataChanged();
    }
}