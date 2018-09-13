package com.syntaxerror.smartbudget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    DBManager mydb = new DBManager(this);
    EditText editName , editAmount ;
    Button newBudget,viewBudget,myStatus,submit;
    TextView budgetName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText)findViewById(R.id.nameEditText);
        editAmount = (EditText)findViewById(R.id.nameEditText);
        submit = (Button) findViewById(R.id.button);

        myStatus = (Button) findViewById(R.id.mystatusbutton);
        newBudget = (Button) findViewById(R.id.newbudgetbutton);
        viewBudget = (Button) findViewById(R.id.viewbudgetbutton);
        budgetName = (TextView) findViewById(R.id.nameOfBudgetText);



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





    }

}
