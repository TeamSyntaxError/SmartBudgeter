package com.syntaxerror.smartbudget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText name,email,password;
    Button signup;
    DBManager MyDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        MyDB = new DBManager(this);

        name = (EditText) findViewById(R.id.nameEditText);
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        signup = (Button) findViewById(R.id.signupButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean rs = MyDB.InsertUser(name.getText().toString(),password.getText().toString(),email.getText().toString(),5000);
                if (rs==false)
                {
                    Toast.makeText(Signup.this,"Insert Failed",Toast.LENGTH_LONG).show();
                }else
                    {
                        Toast.makeText(Signup.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    }

            }
        });
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
