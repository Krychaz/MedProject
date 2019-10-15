package com.cit.medi_screen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;





public class SignUpDatabaseHelper extends SQLiteOpenHelper {
    private static final String databaseName = "mediscreen.db";
    private static  final int databaseVersion = 2;



    public SignUpDatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);



    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
