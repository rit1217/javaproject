package com.login;

import com.local.DatabaseConnection;
import com.local.UserData;
import com.local.UserDatabaseModel;

import java.sql.*;

public class LoginModel {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    LoginModel() throws SQLException {
        con = DatabaseConnection.connectDb();
    }

    public UserData findMatchUser (String username, String userType ) {
        return UserDatabaseModel.findMatchUser( username, userType );
    }

    public void updatePassword( String username , String pwd ) {
        String queryString = "UPDATE user SET Password ='" + pwd + "' WHERE Username ='" + username + "'";
        try {
            pst = con.prepareStatement(queryString);
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
