package com.cit.medi_screen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;


public class DatabaseAccess {
    private PgConnection pg = new PgConnection();
    private SignUpDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;
    Connection connection;


    private DatabaseAccess(Context context) {

        this.dbHelper = new SignUpDatabaseHelper(context);

    }


    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }


    public void open() {
        this.db = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }

    }

    public String getEmail(String email) {
        c = db.rawQuery("select email from registeruser where email='" + email + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();
    }

    public String getPassword(String email) {
        c = db.rawQuery("select password from registeruser where email ='" + email + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();
    }

    public long addUser(String fname, String lName, String email, String password) {
        long res;

        ContentValues values = new ContentValues();
        values.put("firstname", fname);
        values.put("lastname", lName);
        values.put("email", email);
        values.put("password", password);


        res = db.insert("registeruser", null, values);


        return res;
    }


    public String getGP() {
        c = db.rawQuery("select gpname from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();

    }

    public boolean gpExists() {
        boolean res;
        String gp = getGP();
        if (gp.equals("null"))
            res = false;
        else
            res = true;
        return res;
    }

    public long addGP(String gpName, String gpAddress, Integer gpPhone, String gpemail) {
        ContentValues values = new ContentValues();
        values.put("gpname", gpName);
        values.put("gpaddress", gpAddress);
        values.put("gpphone", gpPhone);
        values.put("gpemail", gpemail);


        long res = db.update("registeruser", values, "email='" + SignInActivity.getLoggedInEmail() + "'", null);
        return res;
    }


    public long addInsure(String insureName, Integer insurePhone, String insureEmail) {
        ContentValues values = new ContentValues();
        values.put("insurename", insureName);
        values.put("insurephone", insurePhone);
        values.put("insureemail", insureEmail);


        long res = db.update("registeruser", values, "email='" + SignInActivity.getLoggedInEmail() + "'", null);
        return res;
    }

    public boolean insureExists() {
        boolean res;
        String gp = getInsure();
        if (gp.equals("null"))
            res = false;
        else
            res = true;
        return res;
    }

    public String getInsure() {
        c = db.rawQuery("select insurename from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();
    }

    public String getInsurePhone() {
        c = db.rawQuery("select insurephone from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();
    }

    public String getGPPhone() {
        c = db.rawQuery("select gpphone from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();
    }

    public String getAge() {
        c = db.rawQuery("select age from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();
    }

    public String getGPEmail() {
        c = db.rawQuery("select gpemail from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();
    }

    public String getInsureEmail() {
        c = db.rawQuery("select insureemail from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();
    }

    public boolean medHisExists() {
        boolean res;
        String gp = getAge();
        if (gp.equals("null"))
            res = false;
        else
            res = true;
        return res;

    }
}