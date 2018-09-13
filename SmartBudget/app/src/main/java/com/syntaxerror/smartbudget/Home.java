package com.syntaxerror.smartbudget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

public class Home extends AppCompatActivity {

    private static final String SHARED_DATA_NAME = "sharedData";
    Button go ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        SharedPreferences SPdata = getSharedPreferences(SHARED_DATA_NAME, Context.MODE_PRIVATE);
        boolean hasVisited = SPdata.getBoolean("hasVisited",false);
        boolean hasLoggedIn = SPdata.getBoolean("hasLoggedIn",false);

        if(hasVisited)
        {
            if(hasLoggedIn) {
                startActivity(new Intent(Home.this, MainActivity.class));
                finish();
            }else
                {

                    startActivity(new Intent(Home.this, login.class));
                    finish();
                }

        }else
            {
                showMessage("Welcome","You are new user of SMARTBUDGETER");

            }
        go = (Button) findViewById(R.id.goButton);
        goClick(SPdata);


    }


    private void goClick(final SharedPreferences sharedPreferences)
    {
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("hasVisited",true);
                editor.commit();
                startActivity(new Intent(Home.this, Signup.class));
                finish();


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
