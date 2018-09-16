package com.syntaxerror.smartbudget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.syntaxerror.smartbudget.tables.BudgetTable;
import com.syntaxerror.smartbudget.tables.User;

public class DBManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "SmartBudget.db";
    private static final int DB_VERSION = 1;
    SQLiteDatabase smartbudgetDB;


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
                + User.USER_PASSWORD+ " TEXT,"
                + User.INITIAL_AMOUNT +" INTEGER);");

        // CREATE Budget TABLE
        sqLiteDatabase.execSQL("CREATE TABLE " + BudgetTable.TABLE_NAME
                + " (" + BudgetTable.BUDGET_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BudgetTable.BUDGET_MAIN_ID+ " TEXT,"
                + BudgetTable.BUDGET_NAME+ " TEXT,"
                + BudgetTable.BUDGET_STATUS+ " TEXT,"
                + BudgetTable.BUDGET_AMOUNT+ " INTEGER,"
                + BudgetTable.BUDGET_START_DATE+ " TEXT,"
                + BudgetTable.BUDGET_EMAIL+" TEXT);");


        this.smartbudgetDB = sqLiteDatabase;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        Log.w("LOG_TAG", "Upgrading database from version "
                + i + " to " + i1 + ", which will destroy all old data");
// KILL PREVIOUS TABLES IF UPGRADED
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BudgetTable.TABLE_NAME);
// CREATE NEW INSTANCE OF SCHEMA
        onCreate(sqLiteDatabase);
    }

    public boolean InsertUser(String name, String password, String email,int amount ) {
        smartbudgetDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(User.USER_NAME, name);
        cv.put(User.USER_PASSWORD, password);
        cv.put(User.USER_EMAIL, email);
        cv.put(User.INITIAL_AMOUNT, amount);

        long result = smartbudgetDB.insert(User.TABLE_NAME, User.USER_NAME, cv);
        smartbudgetDB.close();

        if (result==-1)
        {
            return false;
        }else
            {
                return true;
            }
    }

    public boolean InsertBudget(String budgetId,String budgetName,String budgetStatus,int amount,String date ,String budgetEmail)
    {
        smartbudgetDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BudgetTable.BUDGET_MAIN_ID, budgetId);
        cv.put(BudgetTable.BUDGET_NAME, budgetName);
        cv.put(BudgetTable.BUDGET_STATUS, budgetStatus);
        cv.put(BudgetTable.BUDGET_AMOUNT, amount);
        cv.put(BudgetTable.BUDGET_START_DATE, date);
        cv.put(BudgetTable.BUDGET_EMAIL, budgetEmail);

        long result = smartbudgetDB.insert(BudgetTable.TABLE_NAME, BudgetTable.BUDGET_MAIN_ID, cv);
        smartbudgetDB.close();

        if (result==-1)
        {
            return false;
        }else
        {
            return true;
        }

    }

    public Cursor getUser() {
        smartbudgetDB = this.getReadableDatabase();
//Get all users form database
        Cursor c = smartbudgetDB.rawQuery("SELECT *  FROM user", null);
        return c;
    }

    public Cursor getBudget()
    {
        //Get all budgets from database
        smartbudgetDB = this.getReadableDatabase();
        Cursor c = smartbudgetDB.rawQuery("SELECT *  FROM budget", null);
        return c;
    }

    public Cursor getBudgetNames(String email,String status)
    {
        smartbudgetDB = this.getReadableDatabase();
        String sqlQuery = "SELECT * FROM "  + BudgetTable.TABLE_NAME+" WHERE "+BudgetTable.BUDGET_EMAIL + " = '"+ email+"' AND "+BudgetTable.BUDGET_STATUS+" = '"+status+"' " ;
        Cursor c = smartbudgetDB.rawQuery(sqlQuery,null);
        return c;

    }



    public void showMessage (Context context,String title , String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}

