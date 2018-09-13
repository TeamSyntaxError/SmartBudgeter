package com.syntaxerror.smartbudget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

        this.smartbudgetDB = sqLiteDatabase;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        Log.w("LOG_TAG", "Upgrading database from version "
                + i + " to " + i1 + ", which will destroy all old data");
// KILL PREVIOUS TABLES IF UPGRADED
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
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

    public Cursor getUser() {
        smartbudgetDB = this.getReadableDatabase();
// WE ONLY NEED TO RETURN COURSE IDS
        Cursor c = smartbudgetDB.rawQuery("SELECT email , password  FROM user", null);
        return c;
    }
}
