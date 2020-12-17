package com.catalog;

import com.local.ProductData;
import com.local.ProductDatabaseModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class CatalogModel {
    public ArrayList<ProductData> getShirtList () throws SQLException {
        return ProductDatabaseModel.loadProductsFromCategory("Shirt");
    }
    public ArrayList<ProductData> getPantList () throws SQLException {
        return ProductDatabaseModel.loadProductsFromCategory("Pant");
    }
}
