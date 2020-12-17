package com.login;

import com.local.DatabaseConnection;
import com.local.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private ButtonGroup group;
    private JRadioButton customer_radio;
    private JRadioButton admin_radio;
    private JButton signinButton;
    private JButton forgetPasswordButton;
    private JButton signupButton;
    private JButton guestButton;

    /**
     * Create the application.
     */
    public LoginView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        getContentPane().setForeground(UIManager.getColor("Button.light"));
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setResizable(false);
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel Username_Label = new JLabel("Username");
        Username_Label.setBounds(82, 90, 86, 16);
        getContentPane().add(Username_Label);

        JLabel Password_label = new JLabel("Password");
        Password_label.setBounds(82, 127, 67, 16);
        getContentPane().add(Password_label);

        usernameField = new JTextField();
        usernameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user_text = usernameField.getText();
            }
        });
        usernameField.setBounds(162, 85, 130, 26);
        getContentPane().add(usernameField);
        usernameField.setColumns(10);

        signinButton = new JButton("Sign in");
        signinButton.setBounds(82, 173, 117, 29);
        getContentPane().add(signinButton);

        forgetPasswordButton = new JButton("Forget Password");
        forgetPasswordButton.setBounds(211, 173, 130, 29);
        getContentPane().add(forgetPasswordButton);

        signupButton = new JButton("Sign up");
        signupButton.setBounds(82, 214, 117, 29);
        getContentPane().add(signupButton);

        guestButton = new JButton("Guest");
        guestButton.setBounds(211, 214, 130, 29);
        getContentPane().add(guestButton);

        customer_radio = new JRadioButton("Customer");
        customer_radio.setBounds(304, 86, 141, 23);
        getContentPane().add(customer_radio);

        admin_radio = new JRadioButton("Admin");
        admin_radio.setBounds(303, 123, 141, 23);
        getContentPane().add(admin_radio);

        group = new ButtonGroup();
        group.add(admin_radio);
        group.add(customer_radio);
        group.setSelected( customer_radio.getModel(), true );

        passwordField = new JPasswordField();
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] pass_text = passwordField.getPassword();
            }
        });
        passwordField.setBounds(161, 122, 130, 26);
        getContentPane().add(passwordField);

        JLabel lblNewLabel = new JLabel("Shopping App");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblNewLabel.setBounds(162, 18, 160, 53);
        getContentPane().add(lblNewLabel);

    }

    public void addSignInListener ( ActionListener listenToSignInButton ) {
        signinButton.addActionListener( listenToSignInButton );
    }

    public void addSignUpListener ( ActionListener listenToSignUpButton ) {
        signupButton.addActionListener( listenToSignUpButton );
    }

    public void addForgetListener ( ActionListener listenToForgetPwdButton ) {
        forgetPasswordButton.addActionListener( listenToForgetPwdButton );
    }

    public void addGuestListener ( ActionListener listenToGuestButton ) {
        guestButton.addActionListener( listenToGuestButton );
    }

    public String getInputUsername() {
        return usernameField.getText();
    }

    public String getInputPassword() {
        return new String(passwordField.getPassword());
    }

    public String getUserType() {
        if ( customer_radio.isSelected() ) return new String("Customer");
        return new String("Admin" );
    }

    public void showWarning( String errMsg ) {
        JOptionPane.showMessageDialog(null, "Warning :\n" + errMsg);
        passwordField.setText("");
    }

    public String showInputDialog( String message ) {
        return JOptionPane.showInputDialog(null, message);
    }

    public void clearField() {
        usernameField.setText("");
        passwordField.setText("");
        customer_radio.setSelected( true );
    }
}

