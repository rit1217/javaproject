package com.local;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDatabaseModel {

    public static String getNextOrderID() throws SQLException {
        int id = 0;
        Connection con = DatabaseConnection.connectDb();
        String count = "SELECT * FROM orderDB";
        PreparedStatement pst = con.prepareStatement(count);
        ResultSet rs = pst.executeQuery();
        while ( rs.next() ) {
            if ( id < Integer.parseInt(rs.getString("OrderID")));

                id = Integer.parseInt(rs.getString("OrderID"));
        }
        return String.format("%09d", id + 1);
    }

    public static void addOrder( OrderData data ) {
        try {
            String sqlQuery = "INSERT INTO orderDB(OrderID, CustomerUserName, TotalPrice, PaymentType, CreditCardNumber, DeliveryType, Address, Status) VALUES (?,?,?,?,?,?,?,?)";
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement(sqlQuery);
            pst.setString(1, data.getOrderID());
            pst.setString(2, data.getCustomerUserName());
            pst.setFloat(3, data.getTotalPrice());
            pst.setString(4, data.getPaymentType());
            pst.setString(5, data.getCreditCard());
            pst.setString(6, data.getDeliveryType());
            pst.setString(7, data.getAddress());
            pst.setString(8, data.getStatus());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ArrayList<OrderData> getOrderFromCustomerName( String customerUserName ) {
        ArrayList<OrderData> result = new ArrayList<>();
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement( "SELECT * from orderDB");
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                if ( rs.getString("CustomerUserName").equals( customerUserName )) {
                    result.add(new OrderData(rs.getString("OrderID"), rs.getString("CustomerUserName"),
                            rs.getFloat("TotalPrice"), rs.getString("PaymentType"), rs.getString("DeliveryType"),
                            rs.getString("Status")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static ArrayList<OrderData> getAllOrder() {
        ArrayList<OrderData> result = new ArrayList<>();
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement( "SELECT * from orderDB");
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                result.add ( new OrderData( rs.getString("OrderID"), rs.getString( "CustomerUserName"),
                        rs.getFloat( "TotalPrice"), rs.getString("PaymentType"), rs.getString("DeliveryType"),
                        rs.getString("Status")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static OrderData getDataFromOrderID ( String orderID ) {
        OrderData result = null;
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement( "SELECT * from orderDB");
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                if ( rs.getString("OrderID").equals(orderID)) {
                    result = new OrderData(rs.getString("OrderID"), rs.getString("CustomerUserName"),
                            rs.getFloat("TotalPrice"), rs.getString("PaymentType"), rs.getString("DeliveryType"),
                            rs.getString("Status"));
                    if (rs.getString("PaymentType").equals("Credit Card"))
                        result.setCreditCard(rs.getString("CreditcardNumber"));
                    if (rs.getString("DeliveryType").equals("Delivery"))
                        result.setAddress(rs.getString("Address"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static void changeStatus( String orderID, String newStatus ) {
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement( "UPDATE orderDB SET Status = '" + newStatus +"' WHERE OrderID ='" + orderID + "'" );
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
