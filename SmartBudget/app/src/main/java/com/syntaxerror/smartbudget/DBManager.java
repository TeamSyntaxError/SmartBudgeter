package com.syntaxerror.smartbudget;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.syntaxerror.smartbudget.tables.BudgetTable;
import com.syntaxerror.smartbudget.tables.Item;
import com.syntaxerror.smartbudget.tables.User;
import com.syntaxerror.smartbudget.tables.UserBudgetTable;

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


        //CREATE BudgetTable TABLE
        sqLiteDatabase.execSQL("CREATE TABLE" + BudgetTable.TABLE_NAME
                +"(" +BudgetTable.BUDGET_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BudgetTable.BUDGET_NAME + "TEXT"
                + BudgetTable.BUDGET_STATUS +  "TEXT"
                + BudgetTable.BUDGET_AMOUNT + "REAL");

        //CREATE Item TABLE
        sqLiteDatabase.execSQL("CREATE TABLE" + Item.TABLE_NAME
                +"(" +Item.ITEM_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Item.ITEM_NAME + "TEXT"
                + Item.ITEM_DESC + "TEXT"
                + Item.ITEM_DATE + "NUMERIC"
                + Item.ITEM_TIME + "NUMERIC"
                + Item.ITEM_AMOUNT + "REAL");

        //CREATE UserBudgetTable TABLE
        sqLiteDatabase.execSQL("CREATE TABLE" + UserBudgetTable.TABLE_NAME
                +"(" +UserBudgetTable.ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserBudgetTable.USER_ID + "INTEGER"
                + UserBudgetTable.BUDGET_ID + "INTEGER");
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
