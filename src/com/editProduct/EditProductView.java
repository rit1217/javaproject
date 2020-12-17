package com.editProduct;

import com.addProduct.AddProductView;
import com.local.ProductData;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EditProductView extends AddProductView {

    public void setProduct( ProductData product) {
        productNameField.setText( product.getName());
        quantityField.setText(String.valueOf(product.getQuantity()));
        priceField.setText(String.valueOf(product.getPrice()));
        descriptionField.setText( product.getDescription());
        if ( product.getCategory().equals("Shirt")) categoriesCombo.setSelectedIndex( 0 );
        else categoriesCombo.setSelectedIndex( 1 );
        imgBytes = product.getImage();
    }

}
