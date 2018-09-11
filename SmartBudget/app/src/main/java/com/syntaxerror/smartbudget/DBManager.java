package com.syntaxerror.smartbudget;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.syntaxerror.smartbudget.tables.BudgetTable;
import com.syntaxerror.smartbudget.tables.User;

public class DBManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "SmartBudget.db";
    private static final int DB_VERSION = 1;


    DBManager (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // CREATE user TABLE
        sqLiteDatabase.execSQL("CREATE TABLE " + User.TABLE_NAME
                + " (" + User.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + User.USER_NAME+ " TEXT,"
                + User.USER_EMAIL+ " TEXT,"
                + User.USER_NAME+ " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        Log.w("LOG_TAG", "Upgrading database from version "
                + i + " to " + i1 + ", which will destroy all old data");
// KILL PREVIOUS TABLES IF UPGRADED
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BudgetTable.TABLE_NAME);
// CREATE NEW INSTANCE OF SCHEMA
        onCreate(sqLiteDatabase);
    }
}
