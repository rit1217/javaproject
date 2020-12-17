package com.signup;

import com.local.DatabaseConnection;
import com.local.UserData;
import com.local.UserDatabaseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupModel {

    public SignupModel() throws SQLException  {
    }

    public UserData findMatchUser( String username ) {
        return UserDatabaseModel.findMatchUser( username, "Customer" );
    }

    public void insertUserData( UserData data ) {
        UserDatabaseModel.insertUserData( data );
    }
}
