package com.syntaxerror.smartbudget;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "SmartBudget.db";
    private static final int DB_VERSION = 1;
    // NAME OF TABLE YOU WISH TO CREATE
    public static final String TABLE_NAME = "my_table";
    // SOME SAMPLE FIELDS
    public static final String UID = "_id";
    public static final String NAME = "name";

    DBManager (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
