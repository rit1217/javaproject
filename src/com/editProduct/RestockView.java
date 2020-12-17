package com.editProduct;

import com.local.AppController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestockView extends JFrame {

    private JTextField amountField;
    private JButton saveButton;
    private JButton cancelButton;

    public RestockView(  )
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        setBounds(100, 100, 280, 150);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        JLabel amountLbl = new JLabel("Amount");
        amountLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        amountLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        amountLbl.setBounds(27, 34, 73, 17);
        getContentPane().add(amountLbl);

        amountField = new JTextField();
        amountField.setBounds(121, 30, 107, 26);
        getContentPane().add(amountField);
        amountField.setColumns(10);

        saveButton = new JButton("Save");
        saveButton.setBounds(50, 70, 85, 29);
        getContentPane().add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(145, 70, 85, 29);
        getContentPane().add(cancelButton);
    }

    public void addCancelListener( ActionListener listenToCancelButton ) {
        cancelButton.addActionListener( listenToCancelButton );
    }

    public void addSaveListener( ActionListener listenToSaveButton ) {
        saveButton.addActionListener( listenToSaveButton );
    }

    public int getRestockAmount( ) {
        return Integer.parseInt( amountField.getText() );
    }

    public void clearField() {
        amountField.setText( "");
    }
}
