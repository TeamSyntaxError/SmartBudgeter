package com.syntaxerror.smartbudget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.syntaxerror.smartbudget.tables.User;

public class login extends AppCompatActivity {

    private String UserEmail;
    private String UserPassword;
    Button loginButton ;
    EditText email , password;
    DBManager myDB = new DBManager(this);
    Cursor cursor;
    int countUsers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginButton = (Button) findViewById(R.id.loginbutton);
        email = (EditText) findViewById(R.id.emailEditView);
        password = (EditText) findViewById(R.id.passwordEditView);

        cursor = myDB.getUser();
        countUsers = cursor.getCount();

        if (countUsers==0)
        {
            showMessage("NO USERS","No Users in the table!!");

        }
        loginButtonClick();
    }

    private void loginButtonClick()
    {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckUserAccount())
                {
                    CheckAccountPassword();
                }else
                    {
                        showMessage("Not Registered","The email you entered not registered. Signup please");
                    }
            }
        });
    }


    private boolean CheckUserAccount()
    {
        cursor.moveToPosition(-1);
        boolean isAccountRegistered= false;
        String temp = "";
        while (cursor.moveToNext())
        {
            temp = cursor.getString(cursor.getColumnIndex(User.USER_EMAIL));
            if (email.getText().toString().equals(temp)) {
                UserEmail = cursor.getString(cursor.getColumnIndex(User.USER_EMAIL));
                UserPassword = cursor.getString(cursor.getColumnIndex(User.USER_PASSWORD));
                isAccountRegistered = true;
                break;
            }
        }

        return isAccountRegistered;

    }

    private void CheckAccountPassword()
    {

        if (password.getText().toString().equals(UserPassword))
        {
            SharedPreferences SPdata = getSharedPreferences("sharedData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = SPdata.edit();
            editor.putBoolean("hasLoggedIn",true);
            editor.commit();
            startActivity(new Intent(login.this, MainActivity.class));
            finish();

        }else
            {
                Toast.makeText(login.this,"Wrong Password For The Email",Toast.LENGTH_LONG).show();
            }
    }



    public void showMessage (String title , String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
