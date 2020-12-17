package com.profile;

import com.local.AppController;
import com.local.ScreenController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController extends ScreenController {

    ProfileView view;

    public ProfileController (AppController app ) {
        super( app );
        this.view = new ProfileView();

        this.view.addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideScreen();
            }
        });

        this.view.addHistoryListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.switchScreen( app.screens.get(app.PROFILE), app.screens.get(app.HISTORY ));
            }
        });

        this.view.addInfoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.switchScreen( app.screens.get(app.HISTORY ), app.screens.get(app.PROFILE) );
            }
        });

    }

    public void setProfile() {
        view.setProfileView( app.userData );
    }

    @Override
    public JFrame getView() {
        return this.view;
    }

    @Override
    public void showScreen() {
        view.setVisible( true );
    }

    @Override
    public void hideScreen() {
        view.setVisible( false );
    }
}
