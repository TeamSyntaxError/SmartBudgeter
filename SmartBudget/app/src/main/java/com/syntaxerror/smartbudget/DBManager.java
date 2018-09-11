package com.syntaxerror.smartbudget;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.syntaxerror.smartbudget.tables.BudgetTable;
import com.syntaxerror.smartbudget.tables.Item;
import com.syntaxerror.smartbudget.tables.User;
import com.syntaxerror.smartbudget.tables.UserBudgetTable;

import static com.syntaxerror.smartbudget.tables.User.TABLE_NAME;

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
                + User.USER_PASSWORD+ "TEXT"
                + User.INITIAL_AMOUNT+ "TEXT"
                + User.USER_NAME+ " TEXT);");


        //CREATE BudgetTable TABLE
        sqLiteDatabase.execSQL("CREATE TABLE " + BudgetTable.TABLE_NAME
                +"(" +BudgetTable.BUDGET_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BudgetTable.BUDGET_NAME + "TEXT"
                + BudgetTable.BUDGET_STATUS +  "TEXT"
                + BudgetTable.BUDGET_AMOUNT + "REAL);");

        //CREATE Item TABLE
        sqLiteDatabase.execSQL("CREATE TABLE" + Item.TABLE_NAME
                +"(" +Item.ITEM_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Item.ITEM_NAME + "TEXT"
                + Item.ITEM_DESC + "TEXT"
                + Item.ITEM_DATE + "NUMERIC"
                + Item.ITEM_TIME + "NUMERIC"
                + Item.ITEM_AMOUNT + "REAL);");

        //CREATE UserBudgetTable TABLE
        sqLiteDatabase.execSQL("CREATE TABLE " + UserBudgetTable.TABLE_NAME
                +"(" +UserBudgetTable.ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserBudgetTable.USER_ID + "INTEGER"
                + UserBudgetTable.BUDGET_ID + "INTEGER);");
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

    public boolean insertData(String BUDGET_ID, String BUDGET_NAME, String BUDGET_STATUS, String BUDGET_AMOUNT){
        SQLiteDatabase budget = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BUDGET_ID,BUDGET_ID);
        contentValues.put(BUDGET_NAME,BUDGET_NAME);
        contentValues.put(BUDGET_STATUS,BUDGET_STATUS);
        contentValues.put(BUDGET_AMOUNT,BUDGET_AMOUNT);
        long result = budget.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertData(String ITEM_ID,String ITEM_NAME,String ITEM_DESC,String ITEM_DATE,String ITEM_TIME,String ITEM_AMOUNT){
        SQLiteDatabase item = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_ID,ITEM_ID);
        contentValues.put(ITEM_NAME,ITEM_NAME);
        contentValues.put(ITEM_DESC,ITEM_DESC);
        contentValues.put(ITEM_DATE,ITEM_DATE);
        contentValues.put(ITEM_TIME,ITEM_TIME);
        contentValues.put(ITEM_AMOUNT,ITEM_AMOUNT);
        long result = item.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertData(String USER_ID, String USER_NAME ,String USER_EMAIL, String USER_PASSWORD ,String INITIAL_AMOUNT){
        SQLiteDatabase user = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID,USER_ID);
        contentValues.put(USER_NAME,USER_NAME);
        contentValues.put(USER_EMAIL,USER_EMAIL);
        contentValues.put(USER_PASSWORD,USER_PASSWORD);
        contentValues.put(INITIAL_AMOUNT,INITIAL_AMOUNT);
        long result = user.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean insertData(String ID ,String USER_ID,String BUDGET_ID){
        SQLiteDatabase userBudgetTable = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,ID);
        contentValues.put(USER_ID,USER_ID);
        contentValues.put(BUDGET_ID,BUDGET_ID);
        long result = userBudgetTable.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
}
