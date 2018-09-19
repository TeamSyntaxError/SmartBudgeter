package com.syntaxerror.smartbudget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    Button newBudget,viewBudget,myStatus;
    TextView budgetName,amount;
    ImageView logoutImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myStatus = (Button) findViewById(R.id.mystatusbutton);
        newBudget = (Button) findViewById(R.id.newbudgetbutton);
        viewBudget = (Button) findViewById(R.id.viewbudgetbutton);
        budgetName = (TextView) findViewById(R.id.nameOfBudgetText);
        amount = (TextView) findViewById(R.id.amountTextView);
        logoutImage = (ImageView) findViewById(R.id.imageView3);

        SharedPreferences SPdata = getSharedPreferences("sharedData", Context.MODE_PRIVATE);
        String userName = SPdata.getString("userName","User");
        budgetName.setText(userName);
        amount.setText(""+SPdata.getInt("availableCash",00));



        myStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MyStatus.class));
            }
        });

        viewBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ViewBudget.class));

            }
        });

        newBudget.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NewBudget.class));

            }
        });

        logoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences SPdata = getSharedPreferences("sharedData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = SPdata.edit();
                editor.putBoolean("hasLoggedIn",false);
                editor.putString("currentBudgetName","N/A");
                editor.putString("currentBudgetId","N/A");
                editor.putString("userEmail","N/A");
                editor.putString("userName","N/A");
                editor.commit();
                startActivity(new Intent(MainActivity.this,Home.class));
                finish();

            }
        });




    }



}
