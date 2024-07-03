package librarysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianWindow extends JFrame implements LibWindow {
    public static final LibrarianWindow INSTANCE = new LibrarianWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JButton checkoutBookButton;

    private LibrarianWindow() {}

    @Override
    public void init() {
        mainPanel = new JPanel(new GridLayout(1, 1));
        checkoutBookButton = new JButton("Checkout Book");
        checkoutBookButton.addActionListener(new CheckoutBookListener());
        mainPanel.add(checkoutBookButton);
        getContentPane().add(mainPanel);
        setSize(400, 100);
        isInitialized = true;
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
