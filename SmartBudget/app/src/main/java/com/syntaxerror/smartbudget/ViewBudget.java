package com.syntaxerror.smartbudget;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;
import com.syntaxerror.smartbudget.tables.BudgetTable;

public class ViewBudget extends AppCompatActivity{

    private DBManager myDB = new DBManager(this);
    ListView processList,oldList;
    Cursor processCursor,oldCursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_budget);

        processList = (ListView) findViewById(R.id.processBudgetList);
        oldList = (ListView) findViewById(R.id.oldBudgetList);


        SharedPreferences SPdata = getSharedPreferences("sharedData", Context.MODE_PRIVATE);
        String SPemail = SPdata.getString("userEmail","N/A");
        if (SPemail=="N/A")
        {
            Toast.makeText(ViewBudget.this,"No Emails!",Toast.LENGTH_LONG).show();
        }else
        {
            processCursor = myDB.getBudgetNames(SPemail,BudgetTable.OPTION_PROCESS);
            oldCursor = myDB.getBudgetNames(SPemail,BudgetTable.OPTION_CLOSED);
            if(processCursor!=null && oldCursor!=null)
            {
                BudgetListAdapter processBudgetListAdapter = new BudgetListAdapter(getApplicationContext(),processCursor,0);
                processList.setAdapter(processBudgetListAdapter);
                BudgetListAdapter oldBudgetListAdapter = new BudgetListAdapter(getApplicationContext(),oldCursor,0);
                oldList.setAdapter(oldBudgetListAdapter);
        }
        }
    }


}
