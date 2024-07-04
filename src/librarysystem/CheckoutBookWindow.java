package librarysystem;

import business.CheckoutEntry;
import business.CheckoutRecord;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CheckoutBookWindow extends JFrame implements LibWindow {
    public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JTextField memberIdField;
    private JTextField isbnField;
    private JTable checkoutTable;
    private CheckoutTableModel tableModel;
    private JScrollPane tableScrollPane;

    private CheckoutBookWindow() {}

    @Override
    public void init() {
        mainPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Member ID:"));
        memberIdField = new JTextField(15);
        formPanel.add(memberIdField);
        formPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField(15);
        formPanel.add(isbnField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(evt -> submitForm());
        formPanel.add(new JLabel());
        formPanel.add(submitButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        tableModel = new CheckoutTableModel(List.of());
        checkoutTable = new JTable(tableModel);
        tableScrollPane = new JScrollPane(checkoutTable);

        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        setSize(600, 400);
        isInitialized = true;
    }

    private void submitForm() {
        String memberId = memberIdField.getText().trim();
        String isbn = isbnField.getText().trim();

        SystemController sc = new SystemController();
        try {
            CheckoutEntry record = sc.checkoutBook(memberId, isbn);
            JOptionPane.showMessageDialog(this, "Book checked out successfully\nThe due date is: " + record.getDueDate());

            List<CheckoutEntry> entries = sc.getCheckoutEntries();
            tableModel.setCheckoutEntries(entries);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }
}
