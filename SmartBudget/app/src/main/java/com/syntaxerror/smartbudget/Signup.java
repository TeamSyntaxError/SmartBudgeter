package com.syntaxerror.smartbudget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.syntaxerror.smartbudget.tables.BudgetTable;

public class Signup extends AppCompatActivity {
    EditText name,email,password;
    Button signup;
    DBManager MyDB;
    String firstBudgetStartDate ,budgetMainId;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        MyDB = new DBManager(this);

        name = (EditText) findViewById(R.id.nameEditText);
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        signup = (Button) findViewById(R.id.signupButton);

        firstBudgetStartDate =MyDB.GetCurrentDate();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean rs = MyDB.InsertUser(name.getText().toString(),password.getText().toString(),email.getText().toString(),5000);
                if (rs==false)
                {
                    Toast.makeText(Signup.this,"Insert Failed",Toast.LENGTH_LONG).show();
                }else
                    {
                        CreateDefaultBudget();
                        SettingSharedPreferences();
                        Toast.makeText(Signup.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Signup.this, MainActivity.class));
                        finish();
                    }

            }
        });
    }

    private void SettingSharedPreferences()
    {
        SharedPreferences SPdata = getSharedPreferences("sharedData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = SPdata.edit();
        editor.putBoolean("hasLoggedIn",true);
        editor.putString("userEmail",email.getText().toString());
        editor.putString("userName",name.getText().toString());
        editor.putString("currentBudgetId",budgetMainId);
        editor.putString("currentBudgetName",BudgetTable.FIRST_BUDGET_NAME);
        editor.commit();
    }

    public  void CreateDefaultBudget()
    {
        int NoOfBudgets = MyDB.getBudget().getCount();
        NoOfBudgets+=1;
        budgetMainId = MyDB.BudgetMainIdCreate(NoOfBudgets);
        boolean rs = MyDB.InsertBudget(budgetMainId,BudgetTable.FIRST_BUDGET_NAME, BudgetTable.OPTION_PROCESS,5000,firstBudgetStartDate,BudgetTable.FIRST_BUDGET_END_DATE,email.getText().toString());
        if (rs==false)
        {
            Toast.makeText(Signup.this,"Default Budget Creation Failed",Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(Signup.this," DefaultBudget Created Successfully!",Toast.LENGTH_LONG).show();
        }

    }

}
