package com.order;

import com.local.AppController;
import com.local.OrderData;
import com.local.OrderDatabaseModel;
import com.local.ScreenController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminOrderController extends ScreenController {
    AdminOrderView view;

    public AdminOrderController(AppController app) {
        super(app);
        this.view = new AdminOrderView();
        this.view.addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.changeScreen( app.ADMIN );
            }
        });

        this.view.addStatusListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderID = view.getSelectedOrder().getOrderID();
                OrderDatabaseModel.changeStatus( orderID, view.getSelectedStatus() );
            }
        });

        this.view.addViewListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderView( view.getSelectedOrder() ).setVisible( true );
            }
        });
    }
    @Override
    public JFrame getView() {
        return this.view;
    }

    @Override
    public void showScreen() {
        this.view.orderList = OrderDatabaseModel.getAllOrder();
        this.view.updateTable();
        this.view.setVisible( true );
    }

    @Override
    public void hideScreen() {
        this.view.setVisible( false );
    }
}
