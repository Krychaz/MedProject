package com.cit.medi_screen.pgtest;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.cit.medi_screen.SignInActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PgConnection {
SignInActivity sign = new SignInActivity();
    public Connection getCon() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://192.168.108.112:5432/MediDB", "postgres", "");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            return null
            ;


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int pgInsertPatient(String fname, String lName, String email, String password) {

        int row;
        final String SQL_INSERT = "insert into patient values (?,?,?,?)";

        try (
                Connection connection = getCon();
                PreparedStatement myStmt = connection.prepareStatement(SQL_INSERT)) {
            myStmt.setString(3, email);
            myStmt.setString(4, password);
            myStmt.setString(1, fname);
            myStmt.setString(2, lName);
            row = myStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            row = 0;

        }
        return row;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String pgGetEmail(String email) {
        String pgEmail = "";
        String SQL_QUERY = "SELECT email FROM patient WHERE email = ?";
        try (
                Connection connection = getCon();
                PreparedStatement myStmt = connection.prepareStatement(SQL_QUERY)) {
            myStmt.setString(1, email);
            ResultSet rs = myStmt.executeQuery();
            rs.next();
            pgEmail = rs.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }
        return pgEmail;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String pgGetPass(String email) {
        String pgPassword = "";
        String SQL_QUERY = "SELECT password FROM patient WHERE email = ?";
        try (
                Connection connection = getCon();
                PreparedStatement myStmt = connection.prepareStatement(SQL_QUERY)) {
            myStmt.setString(1, email);
            ResultSet rs = myStmt.executeQuery();
            rs.next();
            pgPassword = rs.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }
        return pgPassword;

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String pgGetGP(String email) {
        String pgGP = "";
        String SQL_QUERY = "SELECT gpemail FROM patient WHERE email = ?";
        try (
                Connection connection = getCon();
                PreparedStatement myStmt = connection.prepareStatement(SQL_QUERY)) {
            myStmt.setString(1, email);
            ResultSet rs = myStmt.executeQuery();
            rs.next();
            pgGP = rs.getString("gpemail");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }
        return pgGP;

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int pgInsertGP(String gpName, String gpAddress, String gpEmail, int gpPhone, String loggedInEmail) {

        int row;
        final String SQL_INSERT = "insert into gp values (?,?,?,?)";

        try (
                Connection connection = getCon();
                PreparedStatement myStmt = connection.prepareStatement(SQL_INSERT)) {
            myStmt.setString(1, gpEmail);
            myStmt.setInt(2, gpPhone);
            myStmt.setString(3, gpName);
            myStmt.setString(4, gpAddress);
            row = myStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            row = 0;

        }
        String SQL_UPDATE = "UPDATE patient SET gpemail = ? WHERE email = ?";

        try (
                Connection connection = getCon();
                PreparedStatement myStmt = connection.prepareStatement(SQL_UPDATE)) {
            myStmt.setString(1, gpEmail);
            myStmt.setString(2, loggedInEmail);
            row = myStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            row = 0;

        }

        return row;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int pgInsertInsurer(String insurerName, String insurerEmail, int insurerPhone, String loggedInEmail) {

        int row;
        final String SQL_INSERT = "insert into insurer values (?,?,?)";

        try (
                Connection connection = getCon();
                PreparedStatement myStmt = connection.prepareStatement(SQL_INSERT)) {
            myStmt.setString(1, insurerName);
            myStmt.setString(2, insurerEmail);
            myStmt.setInt(3, insurerPhone);
            row = myStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            row = 0;

        }
        String SQL_UPDATE = "UPDATE patient SET insureremail = ? WHERE email = ?";

        try (
                Connection connection = getCon();
                PreparedStatement myStmt = connection.prepareStatement(SQL_UPDATE)) {
            myStmt.setString(1, insurerEmail);
            myStmt.setString(2, loggedInEmail);
            row = myStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            row = 0;

        }

        return row;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String pgGetInsurer(String email) {
        String pgInsurer = "";
        String SQL_QUERY = "SELECT insureremail FROM patient WHERE email = ?";
        try (
                Connection connection = getCon();
                PreparedStatement myStmt = connection.prepareStatement(SQL_QUERY)) {
            myStmt.setString(1, email);
            ResultSet rs = myStmt.executeQuery();
            rs.next();
            pgInsurer = rs.getString("insureremail");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }
        return pgInsurer;

    }

}

//usb webserver for db
