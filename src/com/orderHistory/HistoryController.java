package com.orderHistory;

import com.local.AppController;
import com.local.OrderDatabaseModel;
import com.local.ScreenController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryController extends ScreenController {

    HistoryView view;
    public HistoryController(AppController app ) {
        super( app );
        view = new HistoryView();
        this.view.addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideScreen();
            }
        });

        this.view.addHistoryListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.switchScreen( app.screens.get(app.PROFILE), app.screens.get(app.HISTORY));
            }
        });

        this.view.addInfoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.switchScreen( app.screens.get(app.HISTORY), app.screens.get(app.PROFILE) );
            }
        });
    }
    @Override
    public JFrame getView() {
        return this.view;

    }

    @Override
    public void showScreen() {
        this.view.historyList = OrderDatabaseModel.getOrderFromCustomerName( app.userData.getUsername() );
        this.view.updateTable();
        this.view.setVisible( true );
    }

    @Override
    public void hideScreen() {
        this.view.setVisible( false );
    }
}
