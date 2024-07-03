package librarysystem;

import javax.swing.*;
import java.awt.*;

public class CheckoutBookWindow extends JFrame implements LibWindow {
    public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;

    private CheckoutBookWindow() {}

    @Override
    public void init() {
        mainPanel = new JPanel(new GridLayout(3, 2));
        mainPanel.add(new JLabel("Member ID:"));
        mainPanel.add(new JTextField(15));
        mainPanel.add(new JLabel("ISBN:"));
        mainPanel.add(new JTextField(15));
        JButton submitButton = new JButton("Submit");
        mainPanel.add(new JLabel());
        mainPanel.add(submitButton);
        getContentPane().add(mainPanel);
        setSize(400, 150);
        isInitialized = true;
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
