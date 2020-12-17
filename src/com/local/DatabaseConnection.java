package com.local;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection con = null;
    public static Connection connectDb() throws SQLException{
        if ( con == null)
            con = DriverManager.getConnection("jdbc:sqlite:/Users/rit/JavaProject/ProjectFinalVersion/src/com/local/user.db");
        System.out.println("connected");
        return con;
    }
}