package com.local;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class ProductDatabaseModel {

    public static ArrayList<ProductData> loadProducts() throws SQLException {
        Connection con = DatabaseConnection.connectDb();
        PreparedStatement pst = con.prepareStatement( "SELECT * FROM product");
        ResultSet rs = pst.executeQuery();

        ArrayList<ProductData> resultList = new ArrayList<>();
        ProductData temp;
        while (rs.next() ){
            temp = new ProductData( rs.getString( "ProductName"),
                    rs.getString("Description"), rs.getInt("Quantity"),
                    rs.getFloat("Price"), rs.getString("Category"));
            temp.setImage( rs.getBytes("Image"));
            resultList.add( temp );
        }

        return resultList;
    }

    public static ArrayList<ProductData> loadProductsFromCategory( String category ) throws SQLException {
        Connection con = DatabaseConnection.connectDb();
        PreparedStatement pst = con.prepareStatement( "SELECT * FROM product");
        ResultSet rs = pst.executeQuery();

        ArrayList<ProductData> resultList = new ArrayList<>();
        ProductData temp;
        while (rs.next() ){
            if ( rs.getString("Category").equals(category)) {
                temp = new ProductData(rs.getString("ProductName"),
                        rs.getString("Description"), rs.getInt("Quantity"),
                        rs.getFloat("Price"), rs.getString("Category"));
                temp.setImage(rs.getBytes("Image"));
                resultList.add(temp);
            }
        }

        return resultList;
    }

    public static void insertProduct( ProductData product , File file) throws SQLException {
        String dbString = "INSERT INTO product(Image, ProductName, Price, Quantity, Category, Description) VALUES (?,?,?,?,?,?)";
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement(dbString);
            FileInputStream inputStream = new FileInputStream(file);
            pst.setBinaryStream(1,(InputStream)inputStream,(int)(file.length()));
//            pst.setBytes(1, product.getImage());
            pst.setString(2,product.getName());
            pst.setFloat(3, product.getPrice());
            pst.setInt(4, product.getQuantity());
            pst.setString(5, product.getCategory());
            pst.setString(6, product.getDescription());
            pst.execute();
        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ProductData findMatchProduct (String productName ) {
        ProductData result = null;
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM product");
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                if ( productName.equals(rs.getString( "ProductName"))) {
                    result = new ProductData( productName, rs.getString( "Description"),
                            rs.getInt("Quantity"), rs.getFloat( "Price"), rs.getString("Category"));
                    result.setImage( rs.getBytes("Image"));
                    break;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static void editProd (String editingProd, int quant ) {
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement( "UPDATE product SET Quantity ='" + quant +"' WHERE ProductName ='" + editingProd + "'" );
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addQuatityProduct( String editingProd, int addQuantity ) {
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement( "UPDATE product SET Quantity = '" + addQuantity +"' WHERE ProductName ='" + editingProd + "'" );
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeByProductName( String prodName ) {
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement( "DELETE FROM product WHERE ProductName ='" + prodName + "'" );
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
