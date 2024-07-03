package librarysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BothRolesWindow extends JFrame implements LibWindow {
    public static final BothRolesWindow INSTANCE = new BothRolesWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JButton addMemberButton;
    private JButton addBookCopyButton;
    private JButton checkoutBookButton;

    private BothRolesWindow() {}

    @Override
    public void init() {
        mainPanel = new JPanel(new GridLayout(3, 1));
        addMemberButton = new JButton("Add Member");
        addMemberButton.addActionListener(new AddMemberListener());
        addBookCopyButton = new JButton("Add Book Copy");
        addBookCopyButton.addActionListener(new AddBookCopyListener());
        checkoutBookButton = new JButton("Checkout Book");
        checkoutBookButton.addActionListener(new CheckoutBookListener());
        mainPanel.add(addMemberButton);
        mainPanel.add(addBookCopyButton);
        mainPanel.add(checkoutBookButton);
        getContentPane().add(mainPanel);
        setSize(400, 200);
        isInitialized = true;
    }

    class AddMemberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddMemberWindow.INSTANCE.init();
            Util.centerFrameOnDesktop(AddMemberWindow.INSTANCE);
            AddMemberWindow.INSTANCE.setVisible(true);
        }
    }

    class AddBookCopyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddBookCopyWindow.INSTANCE.init();
            Util.centerFrameOnDesktop(AddBookCopyWindow.INSTANCE);
            AddBookCopyWindow.INSTANCE.setVisible(true);
        }
    }

    class CheckoutBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CheckoutBookWindow.INSTANCE.init();
            Util.centerFrameOnDesktop(CheckoutBookWindow.INSTANCE);
            CheckoutBookWindow.INSTANCE.setVisible(true);
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
