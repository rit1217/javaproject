package com.cart;

import com.local.AppController;
import com.local.ProductData;
import com.local.ScreenController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CartController extends ScreenController {

    CartView view;
    ArrayList<ProductData> inCartProducts;
    ConfirmOrderController confirmOrder;


    public CartController(AppController app ) {
        super( app );
        this.view = new CartView();
        inCartProducts = new ArrayList<>();

        this.view.addCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideScreen();
            }
        });

        this.view.addConfirmListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( inCartProducts.size() > 0) {
                    //check credit card number
                    if(app.userData.getCardNum().equals("") && view.getRadioButton().equals("credit")){
                         JOptionPane.showMessageDialog(null, "Please register your credit card");
                    }
                    else{
                        confirmOrder.update();
                        app.switchScreen( app.screens.get(app.CART),
                                app.screens.get(app.CONFIRM_ORDER));
                    }
                }

            }
        });

        this.view.addSignCardListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.addScreen( app.SIGN_CARD );
            }
        });

        this.view.addRemoveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inCartProducts.remove( view.getSelectedRow() );
                view.updateCart( inCartProducts );
            }
        });
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

    public void addToCart( ProductData prod ) {
        boolean flag = true;
        for ( ProductData p : inCartProducts ) {
            if ( p.getName().equals( prod.getName() ) && prod.getQuantity() > 0 ) {
                p.setQuantity( prod.getQuantity() );
                flag = false;
            }
        }
        if ( flag && prod.getQuantity() > 0)
            this.inCartProducts.add( prod );
        this.view.updateCart( this.inCartProducts );
    }

    public String getDeliveryType() {
        return this.view.getDeliveryType();
    }

    public String getPaymentType() {
        return this.view.getPaymentType();
    }

    public void setConfirmOrder( ConfirmOrderController confirmOrder ) {
        this.confirmOrder = confirmOrder;
    }

    public float getTotalPrice() {
        float total = 0;
        for( ProductData p : inCartProducts ) {
            total += p.getPrice() * p.getQuantity();
        }
        return total;
    }
}
