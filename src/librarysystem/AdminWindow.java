package librarysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminWindow extends JFrame implements LibWindow {
    public static final AdminWindow INSTANCE = new AdminWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JButton addMemberButton;
    private JButton addBookButton;

    private AdminWindow() {}

    @Override
    public void init() {
        mainPanel = new JPanel(new GridLayout(2, 1));
        addMemberButton = new JButton("Add New Library Member");
        addMemberButton.addActionListener(new AddMemberListener());
        addBookButton = new JButton("Add Book Copy");
        addBookButton.addActionListener(new AddBookListener());
        mainPanel.add(addMemberButton);
        mainPanel.add(addBookButton);
        getContentPane().add(mainPanel);
        setSize(400, 150);
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

    class AddBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddBookCopyWindow.INSTANCE.init();
            Util.centerFrameOnDesktop(AddBookCopyWindow.INSTANCE);
            AddBookCopyWindow.INSTANCE.setVisible(true);
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
