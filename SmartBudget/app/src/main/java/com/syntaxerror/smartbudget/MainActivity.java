package com.syntaxerror.smartbudget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.activeandroid.query.Select;
import com.syntaxerror.smartbudget.model.budgetInfo;

public class MainActivity extends AppCompatActivity {

    Button newBudget ;
    TextView budgetName;
    budgetInfo sample ;
    Button viewBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newBudget = (Button) findViewById(R.id.newbudgetbutton);
        viewBudget = (Button) findViewById(R.id.viewbudgetbutton);
        budgetName = (TextView) findViewById(R.id.nameOfBudgetText);





        viewBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sample = getRandom();
                budgetName.setText(sample.get);
            }
        });

        newBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budgetInfo num2 = new   budgetInfo(002,"second budget");
                num2.save();



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
