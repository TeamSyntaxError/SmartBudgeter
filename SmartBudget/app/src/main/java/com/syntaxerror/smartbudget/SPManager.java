package com.syntaxerror.smartbudget;

import android.content.Context;
import android.content.SharedPreferences;

public class SPManager {
    private static final String SHARED_DATA_NAME = "sharedData";
    private SharedPreferences sharedPreferences;

    SPManager(SharedPreferences sharedPreferences)
    {
        this.sharedPreferences = sharedPreferences;
    }

    public void EditSP(SharedPreferences sharedPreferences ,String key ,String value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public void EditSP(SharedPreferences sharedPreferences ,String key ,boolean value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public void EditSP(SharedPreferences sharedPreferences ,String key ,int value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }


}
