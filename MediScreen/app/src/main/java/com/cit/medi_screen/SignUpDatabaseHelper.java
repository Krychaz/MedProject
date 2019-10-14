package com.cit.medi_screen;

import android.content.Context;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;




public class SignUpDatabaseHelper extends SQLiteAssetHelper {
    private static final String databaseName = "medisreen.db";
    private static final int databaseVersion = 1;



    private static final String tableName = "registeruser";


    public SignUpDatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);

    }

}
