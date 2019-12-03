package com.cit.medi_screen;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.cit.medi_screen.pgtest.PgConnection;


public class DatabaseAccess {
    private PgConnection pg = new PgConnection();
    // private SignUpDatabaseHelper dbHelper;
    //  private SQLiteDatabase db;
    //  private static DatabaseAccess instance;
//    private SignInActivity sign = new SignInActivity();


  /*  private DatabaseAccess(Context context) {

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

   */

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getEmail(String email) {
        /*
        c = db.rawQuery("select email from registeruser where email='" + email + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();

         */


        if (pg.getCon() == null) {
            return "error";
        } else
            return pg.pgGetEmail(email);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public String getPassword(String email) {

      /*  c = db.rawQuery("select password from registeruser where email ='" + email + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();

       */
        if (pg.getCon() == null) {
            return "error";
        } else
            return pg.pgGetPass(email);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public long addUser(String fname, String lName, String email, String password) {
        int row;
        if (pg.getCon() == null) {
            return -2;
        } else
            row = pg.pgInsertPatient(fname, lName, email, password);
        return row;

        /*
        if (row > 0) {
            long res;

            ContentValues values = new ContentValues();
            values.put("firstname", fname);
            values.put("lastname", lName);
            values.put("email", email);
            values.put("password", password);


            res = db.insert("registeruser", null, values);


            return res;
        } else
            return 0;

         */
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getGP() {
       /* c = db.rawQuery("select gpname from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();


        */
        return pg.pgGetGP(SignInActivity.getLoggedInEmail());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean gpExists() {
        boolean res;
        String gp = getGP();
        System.out.println(gp);
        if (gp !=null)
            res = true;
        else
            res = false;
        return res;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public long addGP(String gpName, String gpAddress, Integer gpPhone, String gpEmail) {
        int row = pg.pgInsertGP(gpName, gpAddress, gpEmail, gpPhone, SignInActivity.getLoggedInEmail());
        return row;
        /*if (row > 0) {
            ContentValues values = new ContentValues();
            values.put("gpname", gpName);
            values.put("gpaddress", gpAddress);
            values.put("gpphone", gpPhone);
            values.put("gpemail", gpEmail);


            long res = db.update("registeruser", values, "email='" + SignInActivity.getLoggedInEmail() + "'", null);
            return res;
        } else
            return 0;

         */
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public long addInsure(String insureName, Integer insurePhone, String insureEmail) {
        int row = pg.pgInsertInsurer(insureName, insureEmail, insurePhone, SignInActivity.getLoggedInEmail());
        return row;
       /*
        if (row > 0) {
            ContentValues values = new ContentValues();
            values.put("insurename", insureName);
            values.put("insurephone", insurePhone);
            values.put("insureemail", insureEmail);


            long res = db.update("registeruser", values, "email='" + SignInActivity.getLoggedInEmail() + "'", null);
            return res;
        } else
            return 0;

        */
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean insureExists() {
        boolean res;
        String gp = getInsure();
        if (gp != null)
            res = true;
        else
            res = false;
        return res;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getInsure() {/*
        c = db.rawQuery("select insurename from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();


        */
        return pg.pgGetInsurer(SignInActivity.getLoggedInEmail());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getInsurePhone() {
        /*
        c = db.rawQuery("select insurephone from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();

         */
        return pg.pgGetInsurerPhone(SignInActivity.getLoggedInEmail());
    }

    public String getGPPhone() {/*
        c = db.rawQuery("select gpphone from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();
           */
        return pg.pgGetGPPhone(SignInActivity.getLoggedInEmail());
    }

    public String getAge() {
        /*
        c = db.rawQuery("select age from registeruser where email ='" + SignInActivity.getLoggedInEmail() + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String emailOut = c.getString(0);
            buffer.append("" + emailOut);
        }
        return buffer.toString();

   */
        return pg.pgGetAge(SignInActivity.getLoggedInEmail());
    }


    public boolean medHisExists() {
        boolean res;
        String gp = getAge();
        if (gp.equals(null))
            res = false;
        else
            res = true;
        return res;

    }
}