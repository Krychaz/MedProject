package com.cit.medi_screen.pgtest;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Connection;

public class PG {
    private static PgConnection pg = new PgConnection();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args) {
      //  pg.pgInsertPatient("olly", "poppers", "olly12@mail.com", "pass123");
        //pg.pgInsertGP("cotters", "bantry", "cotters131@mail.com", 123, "olly12@mail.com");
        //pg.pgInsertInsurer("aviva","aviva12@mail.com",123,"olly12@mail.com");
        Connection con = pg.getCon();
        System.out.println(con);


    }
}


