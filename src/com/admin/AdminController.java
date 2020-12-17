package com.admin;

import com.editProduct.EditProductController;
import com.local.AppController;
import com.local.ProductData;
import com.local.ProductDatabaseModel;
import com.local.ScreenController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminController extends ScreenController {
    private AdminView view;
//    private AdminModel model;
    private EditProductController editPage;

    public AdminController(AppController app ) throws SQLException {
        super( app );
//        this.model = new AdminModel();
        this.view = new AdminView( ProductDatabaseModel.loadProducts() );

        this.view.addAddListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.addScreen( app.ADD_PROD );
            }
        });
        this.view.addLogOutListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.clearUserData();
                app.changeScreen( app.LOGIN );
            }
        });
        this.view.addEditListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPage.editingProduct = getSelectedProduct().getName();
                editPage.setProduct( getSelectedProduct() );

                app.addScreen( app.EDIT_PROD );
            }
        });

        this.view.addOrderListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.changeScreen( app.ADMIN_ORDER);
            }
        });

        this.view.addRemoveListener( new RemoveListener() );
        this.view.addRestockListener( new RestockListener() );
        this.view.addRefreshListener( new RefreshListener() );
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

    public void setEditPage(EditProductController editPage) {
        this.editPage = editPage;
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getProductsTableModel().setDataVector(
                    new Object [][] {
                    },
                    new String [] {
                            "Product Name", "Price", "Quantity", "Category"
                    });
            Object[] o;
            try {
                for ( ProductData p : ProductDatabaseModel.loadProducts() ) {
                    o = new Object[]{ p.getName(), p.getPrice(), p.getQuantity(), p.getCategory()};
                    view.getProductsTableModel().addRow( o );
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    class RestockListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            app.addScreen( app.RESTOCK );
        }
    }

    class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductDatabaseModel.removeByProductName( getSelectedProduct().getName() );
        }
    }

    public ProductData getSelectedProduct() {
        return ProductDatabaseModel.findMatchProduct( view.getSelectedRow() );
    }
}
