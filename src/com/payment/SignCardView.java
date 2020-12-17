package com.payment;

import com.local.AppController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignCardView extends JFrame {

    private JTextField cardnumberField;
    private JTextField cardNameField;
    private JTextField expiredField;
    private JPasswordField ccvField;
    private JComboBox<String> cardTypeCombo;
    private ButtonGroup group;
    private JButton cancelButton;
    private JButton confirmButton;

    public SignCardView( )
    {
        initialize();

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        getContentPane().setBackground(Color.LIGHT_GRAY);
        getContentPane().setForeground(Color.LIGHT_GRAY);
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        cardTypeCombo = new JComboBox<>();
        cardTypeCombo.setModel( new DefaultComboBoxModel<>( new String[] { "Visa", "MasterCard", "American Express"}));
        cardTypeCombo.setBounds( 164, 30, 163,26);
        getContentPane().add(cardTypeCombo);

//		JRadioButton visaRadio = new JRadioButton("Visa");
//		visaRadio.setBounds(6, 16, 141, 23);
//		frame.getContentPane().add(visaRadio);
//
//		JRadioButton mastercardRadio = new JRadioButton("MasterCard");
//		mastercardRadio.setBounds(144, 16, 141, 23);
//		frame.getContentPane().add(mastercardRadio);
//
//		JRadioButton AmericanRadio = new JRadioButton("American Express");
//		AmericanRadio.setBounds(297, 16, 147, 23);
//		frame.getContentPane().add(AmericanRadio);

        cardNameField = new JTextField();
        cardNameField.setBounds(164, 70, 163, 26);
        getContentPane().add(cardNameField);
        cardNameField.setColumns(10);

        cardnumberField = new JTextField();
        cardnumberField.setBounds(164, 110, 163, 26);
        getContentPane().add(cardnumberField);
        cardnumberField.setColumns(10);

        expiredField = new JTextField();
        expiredField.setBounds(164, 150, 163, 26);
        getContentPane().add(expiredField);
        expiredField.setColumns(10);

        ccvField = new JPasswordField();
        ccvField.setBounds(164, 190, 64, 26);
        getContentPane().add(ccvField);
        ccvField.setColumns(10);

        confirmButton = new JButton("Confirm ");
        confirmButton.setBounds(179, 243, 117, 29);
        getContentPane().add(confirmButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(308, 243, 117, 29);
        getContentPane().add(cancelButton);

        JLabel ccvLabel = new JLabel("CVV");
        ccvLabel.setBounds(88, 190, 31, 16);
        getContentPane().add(ccvLabel);

        JLabel expiredLabel = new JLabel("Expired Date");
        expiredLabel.setBounds(36, 150, 83, 16);
        getContentPane().add(expiredLabel);

        JLabel cardLabel = new JLabel("Card Number");
        cardLabel.setBounds(36, 110, 83, 16);
        getContentPane().add(cardLabel);

        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(36, 70, 83, 16);
        getContentPane().add(nameLabel);

        JLabel cardTypeLabel = new JLabel("Card Type");
        cardTypeLabel.setBounds(36, 30, 83, 16);
        getContentPane().add(cardTypeLabel);

    }

    public void addCancelListener( ActionListener listenToCancel ) {
        cancelButton.addActionListener( listenToCancel );
    }

    public void addConfirmListener( ActionListener listenToConfirm ) {
        confirmButton.addActionListener( listenToConfirm );
    }

    public void clearField() {
        cardnumberField.setText("");
        cardNameField.setText("");
        expiredField.setText("");
        ccvField.setText("");
        cardTypeCombo.setSelectedIndex(0);
    }
    public String getCardNum(){ return cardnumberField.getText(); }
    public String getCardName(){ return cardNameField.getText(); }
    public String getCardCvv(){ return String.valueOf(ccvField.getPassword()); }


}
