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
import android.widget.TextView;
import android.widget.Toast;
import com.syntaxerror.smartbudget.tables.User;

public class login extends AppCompatActivity {

    private String UserEmail,UserPassword,UserName;
    private int InitialAmount;
    TextView signUp , forget;
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
        signUp  = (TextView) findViewById(R.id.signupTextView);
        forget = (TextView) findViewById(R.id.forgetTextView);

        cursor = myDB.getUser();
        countUsers = cursor.getCount();

        if (countUsers==0)
        {
            myDB.showMessage(this,"NO USERS","No Users in the table!!");

        }
        loginButtonClick(this);
        signupForgetClick();
    }

    private void loginButtonClick(final Context context)
    {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckUserAccount())
                {
                    CheckAccountPassword();
                }else
                    {
                        myDB.showMessage(context,"Not Registered","The email you entered not registered. Signup please");
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
                UserName = cursor.getString(cursor.getColumnIndex(User.USER_NAME));
                InitialAmount = cursor.getInt(cursor.getColumnIndex(User.INITIAL_AMOUNT));
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
            editor.putString("userEmail",UserEmail);
            editor.putString("userName",UserName);
            editor.putInt("initialAmount",InitialAmount);
            editor.putInt("availableCash",InitialAmount);
            editor.commit();
            startActivity(new Intent(login.this, MainActivity.class));
            finish();

        }else
            {
                Toast.makeText(login.this,"Wrong Password For The Email",Toast.LENGTH_LONG).show();
            }
    }

    private void signupForgetClick()
    {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, Signup.class));
                finish();

            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



}
