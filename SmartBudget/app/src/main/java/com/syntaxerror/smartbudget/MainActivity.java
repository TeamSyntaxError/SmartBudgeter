package com.syntaxerror.smartbudget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.activeandroid.query.Select;
import com.syntaxerror.smartbudget.model.budgetInfo;

public class MainActivity extends AppCompatActivity {

    Button newBudget,viewBudget,myStatus;
    TextView budgetName;
    budgetInfo sample ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public static budgetInfo getRandom() {
        return new Select()
                .from(budgetInfo.class)
                .where("budget_id = 001" )
                .executeSingle();
    }
}
