package com.syntaxerror.smartbudget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.syntaxerror.smartbudget.tables.BudgetTable;
import com.syntaxerror.smartbudget.tables.Item;

import java.util.ArrayList;

public class EditBudget extends AppCompatActivity{
    private Button addButton;
    DBManager myDB = new DBManager(this);
    Spinner spinner;
    TextView initial,availableCash,income , expenses;
    private  BudgetListAdapter budgetListAdapter;
    ImageView HomeImage;

    private String budgetSelected;
    private ArrayList<String> BudgetNamesArray,BudgetIdsArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_budget);

        addButton = (Button) findViewById(R.id.addItemButton);
        spinner = (Spinner) findViewById(R.id.spinnerBudgetList);
        HomeImage =(ImageView) findViewById(R.id.homeImageView);
        initial = (TextView) findViewById(R.id.initialCashTextView);
        availableCash = (TextView) findViewById(R.id.availableCashTextView);
        income = (TextView) findViewById(R.id.IncomeTextView);
        expenses = (TextView) findViewById(R.id.ExpencesTextView);

        SharedPreferences SPdata = getSharedPreferences("sharedData", Context.MODE_PRIVATE);
        String SPemail = SPdata.getString("userEmail","N/A");
        if (SPemail=="N/A")
        {
            Toast.makeText(EditBudget.this,"No Emails!",Toast.LENGTH_LONG).show();
        }else
        {

                BudgetNamesArray = myDB.getBudgetNamesAsArray(SPemail,BudgetTable.OPTION_PROCESS);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,BudgetNamesArray);
                spinner.setAdapter(adapter);
                BudgetIdsArray = myDB.getBudgetIdsAsArray(SPemail,BudgetTable.OPTION_PROCESS);

        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String budgetName = BudgetNamesArray.get(position).toString();
                String budgetId= BudgetIdsArray.get(position).toString();
                Toast.makeText(adapterView.getContext(),"budget id of budget is : "+budgetId,Toast.LENGTH_LONG).show();
                int sum = myDB.getSumOfBudget(budgetId, Item.TYPE_Expenses);
                expenses.setText(sum+"");
                SharedPreferences SPdata = getSharedPreferences("sharedData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = SPdata.edit();
                editor.putString("currentBudgetId",budgetId);
                editor.putString("currentBudgetName",budgetName);
                editor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditBudget.this, AddItem.class));
            }
        });

        HomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }



}
