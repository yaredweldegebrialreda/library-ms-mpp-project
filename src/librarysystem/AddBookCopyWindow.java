package librarysystem;

import javax.swing.*;
import java.awt.*;

public class AddBookCopyWindow extends JFrame implements LibWindow {
    public static final AddBookCopyWindow INSTANCE = new AddBookCopyWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;

    private AddBookCopyWindow() {}

    @Override
    public void init() {
        mainPanel = new JPanel(new GridLayout(2, 2));
        mainPanel.add(new JLabel("ISBN:"));
        mainPanel.add(new JTextField(15));
        JButton submitButton = new JButton("Submit");
        mainPanel.add(new JLabel());
        mainPanel.add(submitButton);
        getContentPane().add(mainPanel);
        setSize(400, 100);
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
