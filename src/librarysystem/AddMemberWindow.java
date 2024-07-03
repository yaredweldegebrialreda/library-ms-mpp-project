package librarysystem;

import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import dataaccess.User;

import javax.swing.*;
import java.awt.*;

public class AddMemberWindow extends JFrame implements LibWindow {
    public static final AddMemberWindow INSTANCE = new AddMemberWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;

    private AddMemberWindow() {}

    @Override
    public void init() {
        mainPanel = new JPanel(new GridLayout(7, 2));
        mainPanel.add(new JLabel("Member ID:"));
        mainPanel.add(new JTextField(15));
        mainPanel.add(new JLabel("First Name:"));
        mainPanel.add(new JTextField(15));
        mainPanel.add(new JLabel("Last Name:"));
        mainPanel.add(new JTextField(15));
        mainPanel.add(new JLabel("Street:"));
        mainPanel.add(new JTextField(15));
        mainPanel.add(new JLabel("City:"));
        mainPanel.add(new JTextField(15));
        mainPanel.add(new JLabel("State:"));
        mainPanel.add(new JTextField(15));
        mainPanel.add(new JLabel("Zip:"));
        mainPanel.add(new JTextField(15));
        mainPanel.add(new JLabel("Telephone:"));
        mainPanel.add(new JTextField(15));
        JButton submitButton = new JButton("Submit");
        mainPanel.add(new JLabel());
        mainPanel.add(submitButton);
        addSubmitButtonListener(submitButton);
        getContentPane().add(mainPanel);
        setSize(400, 300);
        isInitialized = true;
    }


    private void addSubmitButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            SystemController sc = new SystemController();
            try {
                //User loggedUser = sc.login(username.getText(), new String(password.getPassword()));
//                sc.addAuthUser(loggedUser);
//                System.out.println(sc.readAuthUser());
//
//                Auth auth = loggedUser.getAuthorization();


                JOptionPane.showMessageDialog(this, "Successful Login");
            } catch (Error e) {
                System.out.println();
                JOptionPane.showMessageDialog(this, "Login failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
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
