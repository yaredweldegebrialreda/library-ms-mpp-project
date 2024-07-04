package librarysystem;

import business.Address;
import business.LibraryMember;
import business.SystemController;

import javax.swing.*;
import java.awt.*;

public class AddMemberWindow extends JFrame implements LibWindow {
    public static final AddMemberWindow INSTANCE = new AddMemberWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JTextField memberIdField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField telephoneField;

    private AddMemberWindow() {}

    @Override
    public void init() {
        mainPanel = new JPanel(new GridLayout(8, 2));
        mainPanel.add(new JLabel("Member ID:"));
        memberIdField = new JTextField(15);
        mainPanel.add(memberIdField);

        mainPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField(15);
        mainPanel.add(firstNameField);

        mainPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(15);
        mainPanel.add(lastNameField);

        mainPanel.add(new JLabel("Street:"));
        streetField = new JTextField(15);
        mainPanel.add(streetField);

        mainPanel.add(new JLabel("City:"));
        cityField = new JTextField(15);
        mainPanel.add(cityField);

        mainPanel.add(new JLabel("State:"));
        stateField = new JTextField(15);
        mainPanel.add(stateField);

        mainPanel.add(new JLabel("Zip:"));
        zipField = new JTextField(15);
        mainPanel.add(zipField);

        mainPanel.add(new JLabel("Telephone:"));
        telephoneField = new JTextField(15);
        mainPanel.add(telephoneField);

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
                String memberId = memberIdField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String street = streetField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String zip = zipField.getText();
                String telephone = telephoneField.getText();

                sc.addMember(new LibraryMember(memberId, firstName, lastName,telephone, new Address(street,city,state, zip)));
                System.out.println(sc.allMemberIds());
                JOptionPane.showMessageDialog(this, "Member added successfully");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to add member: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
