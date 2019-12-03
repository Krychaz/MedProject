package com.cit.medi_screen.pgtest;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Connection;

public class PG {
    private static PgConnection pg = new PgConnection();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args) {
        Connection con = pg.getCon();
        String email = pg.pgGetEmail("jimATmail.com");
       // pg.pgInsertPatient("jim","smith","jimATmail.com","pass");
        System.out.println(con);
        System.out.println(email);

    }
}


