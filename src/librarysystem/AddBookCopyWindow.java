package librarysystem;

import business.CheckoutEntry;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddBookCopyWindow extends JFrame implements LibWindow {
    public static final AddBookCopyWindow INSTANCE = new AddBookCopyWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JTextField isbnField;

    private AddBookCopyWindow() {}

    @Override
    public void init() {
        mainPanel = new JPanel(new GridLayout(2, 2));
        mainPanel.add(new JLabel("ISBN:"));

        isbnField = new JTextField(15);
        mainPanel.add(isbnField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(evt -> submitForm());

        mainPanel.add(new JLabel());
        mainPanel.add(submitButton);
        getContentPane().add(mainPanel);
        setSize(400, 100);
        isInitialized = true;
    }

    private void submitForm() {

        String isbn = isbnField.getText().trim();

        SystemController sc = new SystemController();
        try {
            sc.addBookCopy(isbn);
            JOptionPane.showMessageDialog(this, "Book copy added successfully");

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
