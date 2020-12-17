package com.profile;

import com.local.AppController;
import com.local.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileView extends JFrame {
    private JLabel nameLabel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel addrLabel;
    private JLabel provinceLabel;
    private JLabel zipLabel;
    private JButton backButton;
    private JMenuItem historyMenu;
    private JMenuItem infoMenu;

    public ProfileView()
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setTitle("Profile");
        setBounds(100, 100, 450, 361);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 61, 22);
        getContentPane().add(menuBar);

        JMenu profileMenu = new JMenu("Profile");
        menuBar.add(profileMenu);

        infoMenu = new JMenuItem("Info");
        profileMenu.add(infoMenu);

        JSeparator separator = new JSeparator();
        profileMenu.add(separator);

        historyMenu = new JMenuItem("History");
        profileMenu.add(historyMenu);

        JLabel profileLabel = new JLabel("Profile");
        profileLabel.setFont(new Font("Lucida Grande", Font.BOLD, 21));
        profileLabel.setBounds(20, 45, 122, 16);
        getContentPane().add(profileLabel);

        nameLabel = new JLabel("Name	:	" );
        nameLabel.setBounds(117, 80, 300, 16);
        getContentPane().add(nameLabel);

        usernameLabel = new JLabel("Username	:	");
        usernameLabel.setBounds(93, 105, 300, 16);
        getContentPane().add(usernameLabel);

        emailLabel = new JLabel("Email		:	");
        emailLabel.setBounds(116, 130, 300, 16);
        getContentPane().add(emailLabel);

        phoneLabel = new JLabel("Phone		:	");
        phoneLabel.setBounds(114, 155, 300, 16);
        getContentPane().add(phoneLabel);

        addrLabel = new JLabel("Address	:	");
        addrLabel.setBounds(103, 180, 300, 16);
        getContentPane().add(addrLabel);

        provinceLabel = new JLabel("Province	:	");
        provinceLabel.setBounds(102, 205, 300, 16);
        getContentPane().add(provinceLabel);

        zipLabel = new JLabel("Zip Code	:	");
        zipLabel.setBounds(99, 230, 300, 16);
        getContentPane().add(zipLabel);

        backButton = new JButton("Back");
        backButton.setBounds(327, 304, 117, 29);
        getContentPane().add(backButton);

    }

    public void addHistoryListener( ActionListener listenToHistoryMenu ) {
        historyMenu.addActionListener( listenToHistoryMenu );
    }

    public void addInfoListener( ActionListener listenToInfoMenu ) {
        infoMenu.addActionListener( listenToInfoMenu );
    }

    public void addBackListener( ActionListener listenToBackButton ) {
        backButton.addActionListener( listenToBackButton );
    }

    public void setProfileView(UserData data ){
        nameLabel.setText( nameLabel.getText() + data.getFirstName() + " " + data.getLastName() );
        emailLabel.setText( emailLabel.getText() + data.getEmail());
        usernameLabel.setText( usernameLabel.getText() + data.getUsername());
        phoneLabel.setText( phoneLabel.getText() + data.getPhoneNumber() );
        addrLabel.setText( addrLabel.getText() + data.getAddress() );
        provinceLabel.setText( provinceLabel.getText() + data.getProvince() );
        zipLabel.setText( zipLabel.getText() + data.getZip_code() );
    }
}
