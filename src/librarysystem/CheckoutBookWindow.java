package librarysystem;

import business.SystemController;
import dataaccess.User;

import javax.swing.*;
import java.awt.*;

public class CheckoutBookWindow extends JFrame implements LibWindow {
    public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JTextField memberIdField;
    private JTextField isbnField;

    private CheckoutBookWindow() {}

    @Override
    public void init() {
        mainPanel = new JPanel(new GridLayout(3, 2));
        mainPanel.add(new JLabel("Member ID:"));
        memberIdField = new JTextField(15);
        mainPanel.add(memberIdField);
        mainPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField(15);
        mainPanel.add(isbnField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(evt -> submitForm());
        mainPanel.add(new JLabel());
        mainPanel.add(submitButton);

        getContentPane().add(mainPanel);
        setSize(400, 150);
        isInitialized = true;
    }

    private void submitForm() {
        String memberId = memberIdField.getText().trim();
        String isbn = isbnField.getText().trim();

        // Here you can use memberId and isbn to perform the checkout operation
        // For example:
        SystemController sc = new SystemController();
            try{
                sc.checkoutBook(memberId, isbn);
                JOptionPane.showMessageDialog(this, "Book checked out successfully.");
            }catch (Exception e){
                JOptionPane.showMessageDialog(this,e.getMessage());

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
