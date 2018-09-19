package com.syntaxerror.smartbudget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.syntaxerror.smartbudget.tables.BudgetTable;

public class MyStatus extends AppCompatActivity {
    private ImageView backImageButton;
    DBManager myDB = new DBManager(this);
    private BudgetListAdapter budgetListAdapter;
    ListView  processList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystatus);
        processList = (ListView) findViewById(R.id.budgetslistView);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,days);
       // list.setAdapter(adapter);
        SharedPreferences SPdata = getSharedPreferences("sharedData", Context.MODE_PRIVATE);
        String SPemail = SPdata.getString("userEmail","N/A");
        if (SPemail=="N/A")
        {
            Toast.makeText(MyStatus.this,"No Emails!",Toast.LENGTH_LONG).show();
        }else
        {
            Cursor cursor = myDB.getBudgetNames(SPemail,BudgetTable.OPTION_PROCESS);
            if(cursor!=null)
            {
                budgetListAdapter = new BudgetListAdapter(getApplicationContext(),cursor,0);
                processList.setAdapter(budgetListAdapter);
            }
        }

        processList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                startActivity(new Intent(MyStatus.this, EditBudget.class));

            }
        });

        backImageButton = (ImageView) findViewById(R.id.backImageView);
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
}
