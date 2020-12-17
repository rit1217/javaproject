package com.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDatabaseModel {
    public static void insertItem( OrderItemData item ) {
        try { String sqlQuery = "INSERT INTO orderItems(ProductName, OrderID, Quantity, Price) VALUES (?,?,?,?)";
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement(sqlQuery);
            pst.setString(1, item.getProdName());
            pst.setString(2, item.getOrderID());
            pst.setInt(3, item.getQuantity());
            pst.setFloat(4, item.getTotalPrice());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ArrayList<OrderItemData> getItemsFromOrderID ( String orderID ) {
        ArrayList<OrderItemData> result = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM orderItems";
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                if ( orderID.equals(rs.getString( "OrderID"))) {
                    result.add( new OrderItemData( rs.getString("ProductName"), rs.getString( "OrderID"),
                            rs.getInt("Quantity"), rs.getFloat("Price")) );

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

}
