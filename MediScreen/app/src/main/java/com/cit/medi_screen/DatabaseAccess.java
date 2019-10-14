package com.cit.medi_screen;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccess {
    private SignUpDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

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


}

