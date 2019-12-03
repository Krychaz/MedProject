package com.cit.medi_screen.pgtest;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PgConnection {
    public Connection getCon() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://192.168.0.44:5432/medi_screen_db", "postgres", "");
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
        final String SQL_INSERT = "insert into patient values  (?,?,?,?)";

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

    /* @RequiresApi(api = Build.VERSION_CODES.KITKAT)
     public String pgGetall() {
         String pgEmail = "no res";
         String SQL_QUERY = "SELECT * FROM patient_login ";

         try (
                 PreparedStatement myStmt = connection.prepareStatement(SQL_QUERY)) {
             ResultSet rs = myStmt.executeQuery();
             if(rs.next()) {
                 pgEmail = rs.getString("first_name");
             }
             else {

             }
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println(e);

         }
         return pgEmail;

     }

     */
    private String getEmailRes(ResultSet rs) throws SQLException {
        String email = " no res 1";
        System.out.println(rs);
        if (rs.next()) {
            email = rs.getString("email");


        }
        return email;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String pgGetEmail(String email) {
        String pgEmail = "no res";
        String SQL_QUERY = "SELECT email FROM patient WHERE email = ?";

        System.out.println(email);

        try (Connection connection = getCon();
             PreparedStatement myStmt = connection.prepareStatement(SQL_QUERY)) {
            //System.out.println(myStmt);

            myStmt.setString(1, email);
            ResultSet rs = myStmt.executeQuery();
            pgEmail = getEmailRes(rs);
            // System.out.println(pgEmail);
            // if (rs.next()) {
            //   pgEmail = rs.getString("email");
            //} else {

            //}
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
            myStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

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
            myStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String pgGetInsurerPhone(String email) {
        String pgInsurerphone = " ";
        String SQL_QUERY =
                "select i.insurerphone from insurer i inner join patient p on i.insureremail = p.insureremail where p.email =  ?";
        try (
                Connection connection = getCon();
                PreparedStatement myStmt = connection.prepareStatement(SQL_QUERY)) {
            myStmt.setString(1, email);
            ResultSet rs = myStmt.executeQuery();
            rs.next();
            pgInsurerphone = rs.getString("insurerphone");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }
        System.out.println(pgInsurerphone);
        return pgInsurerphone;

    }

    public String pgGetGPPhone(String loggedInEmail) {
        return "aye";
    }

    public String pgGetAge(String loggedInEmail) {
        return "aye";

    }
}


//usb webserver for db
