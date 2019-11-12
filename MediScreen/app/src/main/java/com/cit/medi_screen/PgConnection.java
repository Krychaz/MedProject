package com.cit.medi_screen;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PgConnection {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int pgInsert(String fname, String lName, String email, String password) {

        int row;
        final String SQL_INSERT = "insert into patient values (?,?,?,?)";

        try (
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/medi_screen_db", "postgres", "******");
                PreparedStatement myStmt = connection.prepareStatement(SQL_INSERT)) {
            myStmt.setString(1, email);
            myStmt.setString(2, password);
            myStmt.setString(3, fname);
            myStmt.setString(4, lName);
            row = myStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            row = 0;

        }
        return row;
    }
}