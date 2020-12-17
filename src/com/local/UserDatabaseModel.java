package com.local;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseModel {

    public static void insertUserData( UserData data ) {
        try { String sqlQuery = "INSERT INTO user(Email, Username, Password, Phone, Address, FirstName, LastName, AccType, Province, PostalCode) VALUES (?,?,?,?,?,?,?,?,?,?)";
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement(sqlQuery);
            pst.setString(1, data.getEmail());
            pst.setString(2, data.getUsername());
            pst.setString(3, data.getPassword());
            pst.setString(4, data.getPhoneNumber());
            pst.setString(5, data.getAddress());
            pst.setString(6, data.getFirstName());
            pst.setString(7, data.getLastName());
            pst.setString(8, "Customer");
            pst.setString(9, data.getProvince());
            pst.setString(10,data.getZip_code());

            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void insertCard(UserData data){
        try { String sqlQuery = "UPDATE user SET CreditCardNumber ='" + data.getCardNum() +
                "', CreditCardName = '" + data.getCardName() +
                "', CreditCardCvv = '" + data.getCardCvv()+ "'" +
                " WHERE Username ='" + data.getUsername() + "'";

                Connection con = DatabaseConnection.connectDb();
                PreparedStatement pst = con.prepareStatement(sqlQuery);
                pst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }




    public static UserData findMatchUser (String username, String userType ) {
        UserData result = null;
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                if ( username.equals(rs.getString( "Username")) &&
                        userType.equals( rs.getString("AccType"))) {
                    result = new UserData(username, rs.getString( "Password"), rs.getString("Email"), rs.getString("Phone"),
                            rs.getString("FirstName"), rs.getString("LastName"));
                    result.setAddress(rs.getString("Address"), rs.getString( "Province")
                            , rs.getString("PostalCode"));
                    if ( userType.equals("Admin") ) result.setAdmin();
                    break;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static String cardNum(String userName){
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                if ( userName.equals(rs.getString( "Username"))) {
                    return rs.getString("CreditCardNumber");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    public static String cardName(String userName){
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                if ( userName.equals(rs.getString( "Username"))) {
                    return rs.getString("CreditCardName");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    public static String cardCvv(String userName){
        try {
            Connection con = DatabaseConnection.connectDb();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
                if ( userName.equals(rs.getString( "Username"))) {
                    return rs.getString("CreditCardCvv");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }





}
