package com.cart;

import com.local.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ConfirmOrderController extends ScreenController {

    CartController cart;
    ConfirmOrderView view;

    public ConfirmOrderController(AppController app, CartController cart ) {
        super( app );
        this.view = new ConfirmOrderView();
        this.cart = cart;

        this.view.addCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.changeScreen( app.CART );
            }
        });

        this.view.addConfirmListener( new ConfirmListener() );
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

    public void update() {
        this.view.updateOrder( this.cart.inCartProducts, this.cart.getDeliveryType(), this.cart.getPaymentType(), app.userData );
    }

    class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderDatabaseModel.addOrder( getOrderData() );
            JOptionPane.showMessageDialog( null,"Successful!");
            ProductData temp;
            for ( ProductData p : cart.inCartProducts ) {
                temp = ProductDatabaseModel.findMatchProduct( p.getName() );
                temp.setQuantity( temp.getQuantity() - p.getQuantity() );
                ProductDatabaseModel.editProd( temp.getName(), temp.getQuantity() );
            }
            app.changeScreen( app.CATALOG );
        }
    }

    public OrderData getOrderData() {
        String orderID = null;
        try {
            orderID = OrderDatabaseModel.getNextOrderID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        OrderData data = new OrderData( orderID , app.userData.getUsername(), cart.getTotalPrice(),
                cart.getPaymentType(), cart.getDeliveryType(), "Pending");
        if ( cart.getDeliveryType().equals( "Delivery" )) {
            data.setAddress( app.userData.getAddress(), app.userData.getProvince(), app.userData.getZip_code() );
        }
        if ( cart.getPaymentType().equals( "Credit Card")) {
            data.setCreditCard( app.userData.getCardNum() );
        }
        OrderItemData temp;
        for (ProductData p : cart.inCartProducts) {
            temp = new OrderItemData( p.getName(), orderID, p.getQuantity(), p.getPrice() * p.getQuantity() );
            data.addItem(temp );
            OrderItemDatabaseModel.insertItem( temp );
        }
        return data;
    }
}
