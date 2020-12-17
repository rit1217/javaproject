package com.catalog;

import com.local.AppController;
import com.local.ScreenController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CatalogController extends ScreenController {

    CatalogView view;
    CatalogModel model;

    public CatalogController(AppController app) throws SQLException {
        super( app );
        this.model = new CatalogModel();
        this.view = new CatalogView( model.getShirtList(), model.getPantList(), app.cart );

        this.view.addProfileListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.addScreen( app.PROFILE );
            }
        });

        this.view.addCartListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.addScreen( app.CART );
            }
        });

        this.view.addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.changeScreen( app.LOGIN );
                app.guestLogin = false;
            }
        });
        this.view.addClearListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setPreview( null, "Shirt");
                view.setPreview(null, "Pant");
            }
        });
    }

    public void setUserView( String UserType ) {
        if ( UserType.equals("Customer") ) this.view.userLogin();
        else this.view.guestLogin();
    }

    @Override
    public JFrame getView() {
        return view;
    }

    @Override
    public void showScreen() {
        updateCatalog();
        view.setVisible( true );
    }

    @Override
    public void hideScreen() {
        view.setPreview( null, "Shirt");
        view.setPreview(null, "Pant");
        view.setVisible( false );
    }

    public void updateCatalog() {
        try {
            this.view.updateCatalog( model.getShirtList(), model.getPantList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
