package com.signup;

import com.local.AppController;
import com.local.DatabaseConnection;
import com.local.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignupView extends JFrame {
    private JTextField firstnameField;
    private JTextField lastnameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField provinceField;
    private JTextField postcodeField;
    private JButton confirmButton;
    private JButton cancelButton;

    public SignupView() {
        initialize();
    }

    private void initialize() {
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setResizable(false);
        setBounds(100, 100, 450, 361);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel Firstname_label = new JLabel("First Name");
        Firstname_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Firstname_label.setBounds(88, 15, 75, 16);
        getContentPane().add(Firstname_label);

        JLabel Lastname_label = new JLabel("Last Name");
        Lastname_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Lastname_label.setBounds(88, 43, 75, 16);
        getContentPane().add(Lastname_label);

        JLabel Email_label = new JLabel("Email");
        Email_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Email_label.setBounds(129, 71, 34, 16);
        getContentPane().add(Email_label);

        JLabel Username_label = new JLabel("Username");
        Username_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Username_label.setBounds(101, 99, 62, 16);
        getContentPane().add(Username_label);

        JLabel Password_label = new JLabel("Password");
        Password_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Password_label.setBounds(102, 127, 61, 16);
        getContentPane().add(Password_label);

        JLabel Confirm_label = new JLabel("Confirm Password");
        Confirm_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Confirm_label.setBounds(33, 155, 130, 16);
        getContentPane().add(Confirm_label);

        JLabel Phone_label = new JLabel("Phone");
        Phone_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Phone_label.setBounds(96, 183, 67, 16);
        getContentPane().add(Phone_label);

        JLabel Address_label = new JLabel("Address");
        Address_label.setHorizontalAlignment(SwingConstants.RIGHT);
        Address_label.setBounds(112, 211, 51, 16);
        getContentPane().add(Address_label);

        JLabel lblProvince = new JLabel("Province");
        lblProvince.setHorizontalAlignment(SwingConstants.RIGHT);
        lblProvince.setBounds(102, 239, 61, 16);
        getContentPane().add(lblProvince);

        JLabel lblPostalCode = new JLabel("Postal Code");
        lblPostalCode.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPostalCode.setBounds(46, 267, 117, 16);
        getContentPane().add(lblPostalCode);

        firstnameField = new JTextField();
        firstnameField.setColumns(10);
        firstnameField.setBounds(189, 10, 212, 26);
        getContentPane().add(firstnameField);

        lastnameField = new JTextField();
        lastnameField.setBounds(189, 38, 212, 26);
        getContentPane().add(lastnameField);
        lastnameField.setColumns(10);

        emailField = new JTextField();
        emailField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email_text = emailField.getText();
            }
        });
        emailField.setBounds(189, 66, 212, 26);
        getContentPane().add(emailField);
        emailField.setColumns(10);

        usernameField = new JTextField();
        usernameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user_text = usernameField.getText();
            }
        });
        usernameField.setBounds(189, 94, 212, 26);
        getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pass_text = passwordField.getText();
            }
        });
        passwordField.setBounds(189, 122, 212, 26);
        getContentPane().add(passwordField);
        passwordField.setColumns(10);

        confirmField = new JPasswordField();
        confirmField.setBounds(189, 150, 212, 26);
        getContentPane().add(confirmField);
        confirmField.setColumns(10);

        phoneField = new JTextField();
        phoneField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String phone_text = phoneField.getText();
            }
        });
        phoneField.setBounds(189, 178, 212, 26);
        getContentPane().add(phoneField);
        phoneField.setColumns(10);

        addressField = new JTextField();
        addressField.setColumns(10);
        addressField.setBounds(189, 206, 212, 26);
        getContentPane().add(addressField);

        provinceField = new JTextField();
        provinceField.setColumns(10);
        provinceField.setBounds(189, 234, 212, 26);
        getContentPane().add(provinceField);

        postcodeField = new JTextField();
        postcodeField.setColumns(10);
        postcodeField.setBounds(189, 262, 212, 26);
        getContentPane().add(postcodeField);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(100, 304, 110, 29);
        getContentPane().add(confirmButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(240, 304, 110, 29);
        getContentPane().add(cancelButton);


    }

    public void addConfirmListener( ActionListener listenToConfirmButton ) {
        confirmButton.addActionListener( listenToConfirmButton );
    }

    public void addCancelListener( ActionListener listenToCancelButton ) {
        cancelButton.addActionListener( listenToCancelButton );
    }

    public UserData getInputInfo() {
        UserData userInput = new UserData( usernameField.getText(), new String(passwordField.getPassword() ),
                emailField.getText(), phoneField.getText(), firstnameField.getText(), lastnameField.getText() );
        userInput.setAddress( addressField.getText(), provinceField.getText(), postcodeField.getText() );

        return userInput;
    }

    public String getConfirmPassword() {
        return new String( confirmField.getPassword() );
    }

    public void showWarning( String errMsg ) {
        JOptionPane.showMessageDialog(null, "Warning :\n" + errMsg);
        passwordField.setText("");
    }

    public void clearUserNameField() {
        usernameField.setText("");
    }

    public void clearPasswordField() {
        passwordField.setText("");
        confirmField.setText("");
    }

    public void clearAllField() {
        usernameField.setText("");
        passwordField.setText("");
        confirmField.setText("");
        addressField.setText("");
        emailField.setText("");
        phoneField.setText("");
        firstnameField.setText("");
        lastnameField.setText("");
        provinceField.setText("");
        postcodeField.setText("");
    }
}
