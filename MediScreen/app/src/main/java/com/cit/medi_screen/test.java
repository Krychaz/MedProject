package com.cit.medi_screen;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class test {
    private static PgConnection pg = new PgConnection();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args) {
        pg.pgInsert("a","a","a","a");

    }
}
