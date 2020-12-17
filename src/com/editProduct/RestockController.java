package com.editProduct;

import com.admin.AdminController;
import com.local.AppController;
import com.local.ProductDatabaseModel;
import com.local.ScreenController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestockController extends ScreenController {

    RestockView view;
    AdminController adminApp;

    public RestockController(AppController app, AdminController adminApp) {
        super( app );
        this.view = new RestockView();
        this.adminApp = adminApp;

        this.view.addCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideScreen();
            }
        });

        this.view.addSaveListener( new SaveRestockListener() );
    }
    @Override
    public JFrame getView() {
        return this.view;
    }

    @Override
    public void showScreen() {
        this.view.setVisible( true );
    }

    @Override
    public void hideScreen() {
        this.view.setVisible( false );
    }

    class SaveRestockListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductDatabaseModel.addQuatityProduct( adminApp.getSelectedProduct().getName(),
                    adminApp.getSelectedProduct().getQuantity() + view.getRestockAmount() );
            view.clearField();
            hideScreen();
        }
    }
}
