package com.login;

import com.cart.CartController;
import com.local.AppController;
import com.local.ScreenController;
import com.local.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController extends ScreenController {

    LoginView view;
    LoginModel model;

    public LoginController(AppController app ) throws SQLException {
        super( app );
        this.view = new LoginView();
        this.model = new LoginModel();

        this.view.addSignInListener(new SignInListener() );
        this.view.addSignUpListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.changeScreen( app.SIGNUP );
            }
        });
        this.view.addForgetListener(new ForgetPwdListener() );
        this.view.addGuestListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.guestLogin = true;
                app.changeScreen( app.CATALOG );
                view.clearField();
            }
        });
    }

    public void showScreen() {
        view.setVisible( true );
    }

    public void hideScreen() {
        view.setVisible( false );
    }

    public JFrame getView() {
        return this.view;
    }

    class SignInListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserData tempUser;
            tempUser = model.findMatchUser( view.getInputUsername(), view.getUserType() );
            if ( tempUser == null ) {
                UserData checkUser = model.findMatchUser( view.getInputUsername(), "Admin");
                if(checkUser!=null){
                    view.showWarning("\t-You are an admin.\n");
                }
                else {
                    view.showWarning("\t- User does not exist.\n");
                }
            }
            else if ( !tempUser.getPassword().equals(view.getInputPassword())) {
                view.showWarning("\t- Incorrect Password.\n" );
            }
            else if ( tempUser.getAccType().equals("Admin")){
                app.userData = tempUser;
                view.clearField();
                app.changeScreen(app.ADMIN);
            }
            else if ( tempUser.getAccType().equals("Customer")) {
                app.userData = tempUser;
                app.guestLogin = false;
                view.clearField();
                app.changeScreen(app.CATALOG);
                app.profileScreen.setProfile();
            }
        }
    }

    class ForgetPwdListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserData tempUser;
            tempUser = model.findMatchUser( view.getInputUsername(), view.getUserType());
            if ( tempUser == null ) {
                view.showWarning( "\t- User does not exist.\n");
            }
            else {
                SendEmail sendEmail = new SendEmail( tempUser.getEmail() );
                sendEmail.run();
                String input;
                boolean correctNum = false;
                do{
                    input = view.showInputDialog("Please enter the OTP sent through your email.");
                    //if user inputs correct random number sent through email
                    if (input.equals(String.valueOf(sendEmail.getRandom()))) {
                        correctNum = true;
                        boolean matchPassword = false;
                        do {
                            //reset password process
                            String password = view.showInputDialog("Please enter a new password.");
                            if (password.length() < 6)
                                view.showWarning( "\t- Password must be at least 6 characters\n" );
                            else {
                                String cfPass = view.showInputDialog( "Please re-enter your password.");
                                if (password.equals(cfPass)) {
                                    //check valid password before insertion
                                    model.updatePassword( tempUser.getUsername(), password );
                                    matchPassword = true;
                                } else {
                                    view.showWarning( "\t- Passwords do not match.");
                                }

                            }
                        } while (!matchPassword);
                    }

                    //wrong number
                    else {
                        view.showWarning( "OTP does not correct.");
                    }
                }while(!correctNum);
            }
        }
    }

}
