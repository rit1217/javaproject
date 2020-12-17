package com.signup;

import com.local.AppController;
import com.local.PasswordVerifier;
import com.local.ScreenController;
import com.local.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignupController extends ScreenController {

    SignupModel model;
    SignupView view;

    public SignupController(AppController app ) throws SQLException {
        super(app);
        this.model = new SignupModel();
        this.view = new SignupView();

        this.view.addConfirmListener( new ConfirmListener() );
        this.view.addCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearAllField();
                app.changeScreen( app.LOGIN );
            }
        });
    }


    @Override
    public JFrame getView() {
        return view;
    }

    @Override
    public void showScreen() {
        view.setVisible( true );

    }

    @Override
    public void hideScreen() {
        view.setVisible( false );
    }

    class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserData inputInfo = view.getInputInfo();
            String errorMsg = "";
            boolean passValid = true;
            boolean userValid = true;
            //check input before inserting data
            String userName = inputInfo.getUsername();
            if ( model.findMatchUser( userName ) != null ){
                errorMsg += "\t- Username already existed.\n";
                userValid = false;
            }

            //check whether the username contains at least 4 characters
            if( !PasswordVerifier.isMinimumLength( userName )) {
                errorMsg += "\t- The username must be at least 4 characters.\n";
                userValid = false;
            }
            //check whether the string contains special character
            if( !PasswordVerifier.hasLegalChars( userName )) {
                errorMsg += "\t- The username has a special characters.\n";
                userValid = false;
            }

            //check whether the passwords are match
            String pass = inputInfo.getPassword();
            //if password doesn't match
            if(!pass.equals( view.getConfirmPassword() )) {
                errorMsg += "\t- Password does not match\n";
                passValid = false;
            }

            //check whether the password contains at least 6 characters
            if(pass.length() < 6) {
                errorMsg += "\t- Password must be at least 6 characters.\n";
                passValid = false;
            }

            //check whether the user fill every information box
            if(inputInfo.getUsername().length() == 0 || inputInfo.getEmail().length() == 0
                    || inputInfo.getPassword().length() == 0
                    || inputInfo.getPhoneNumber().length() == 0
                    || inputInfo.getAddress().length() == 0 ||
                    inputInfo.getProvince().length() == 0 ||
                    inputInfo.getZip_code().length() == 0) {
                errorMsg += "\t- Please fill out every field.\n";
            }
            //if all the inputs are valid, then save the data
            else if( userValid && passValid ) {
                model.insertUserData( inputInfo );
                JOptionPane.showMessageDialog(null, "Sign up successful!");
                app.changeScreen( app.LOGIN );
            }
            else{
                //show error inputs
                view.showWarning( errorMsg);
                //clear inputs
                if ( !userValid )
                    view.clearUserNameField();
                if ( !passValid ) {
                    view.clearPasswordField();
                }
                errorMsg = "";
            }
        }
    }
}
