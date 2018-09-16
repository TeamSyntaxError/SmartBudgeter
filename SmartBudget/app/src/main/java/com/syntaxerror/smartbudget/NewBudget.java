package com.syntaxerror.smartbudget;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.syntaxerror.smartbudget.tables.BudgetTable;


public class NewBudget extends AppCompatActivity {
    private DBManager MyDB;
    private ImageView backImageButton;
    private EditText budgetName ,amountOfBudget,endDate;
    private Button create;
    private String phBudgetName,phEndDate,phAmount,phBudgetMainId,phEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget);

        MyDB = new DBManager(this);
        backImageButton = (ImageView) findViewById(R.id.backImageView);
        budgetName = (EditText) findViewById(R.id.nameofbudgetEditText);
        amountOfBudget = (EditText) findViewById(R.id.moneyEditText);
        endDate = (EditText) findViewById(R.id.enddateEditText);
        create = (Button) findViewById(R.id.createButton);

        SharedPreferences SPdata = getSharedPreferences("sharedData", Context.MODE_PRIVATE);
        phEmail = SPdata.getString("userEmail","N/A");
        if (phEmail=="N/A")
        {
            Toast.makeText(NewBudget.this,"Email Address Not Saved",Toast.LENGTH_LONG).show();
        }






        CreateClick();
        BackClick();

    }

    public void BackClick()
    {
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void CreateClick()
    {
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = MyDB.getBudget();
                int noOfRows = cursor.getCount();
                phBudgetMainId = BudgetMainIdCreate(noOfRows);
                phBudgetName = budgetName.getText().toString();
                phAmount = amountOfBudget.getText().toString();
                int findAmount = Integer.parseInt(phAmount);
                phEndDate = endDate.getText().toString();

                boolean rs = MyDB.InsertBudget(phBudgetMainId,phBudgetName,BudgetTable.OPTION_PROCESS,findAmount,phEndDate,phEmail);
                if (rs==false)
                {
                    Toast.makeText(NewBudget.this,"Budget Creation Failed",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(NewBudget.this,"Budget Created Successfully!",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(NewBudget.this, MainActivity.class));
                    finish();
                }

            }
        });
    }

    public String BudgetMainIdCreate(int noOfRows)
    {
        String part1 = "SB";
        String part2 = String.format("%04d",noOfRows);
        return part1+part2;
    }
}
