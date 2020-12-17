package com.payment;

import com.local.AppController;
import com.local.ScreenController;
import com.signup.SignupController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignCardController extends ScreenController {

    SignCardView view;

    public SignCardController(AppController app ){
        super( app );
        this.view = new SignCardView();

        this.view.addCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.clearField();

                hideScreen();
            }
        });

        this.view.addConfirmListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.userData.setCard(view.getCardNum(), view.getCardName(), view.getCardCvv());
                hideScreen();

            }
        });
    }

    @Override
    public JFrame getView() {
        return view;
    }

    @Override
    public void showScreen() {
        this.view.setVisible( true );
    }

    @Override
    public void hideScreen() {
        this.view.setVisible( false );
    }
}
