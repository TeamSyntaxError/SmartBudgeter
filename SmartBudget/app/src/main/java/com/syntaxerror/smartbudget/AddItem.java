package com.syntaxerror.smartbudget;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddItem extends AppCompatActivity {

    private Calendar calendar;
    private SimpleDateFormat DateFormat,TimeFormat;
    private String Name,Description,Type,Date,Time,Budget_Main_Id,Amount;
    String[] list;
    TextView defaultHead;
    EditText amountEditText,descEditText;
    Spinner spinner ;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button addButton ,cancelButton;
    DBManager myDB = new DBManager(this);
    private String Budget_Name;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        spinner = (Spinner) findViewById(R.id.typeSpinner);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        defaultHead = (TextView) findViewById(R.id.defaultHeadTextView);
        amountEditText = (EditText) findViewById(R.id.amountAddItemEditText);
        descEditText = (EditText) findViewById(R.id.descriptionAddItemEditText);
        addButton = (Button) findViewById(R.id.addButtonItem);
        cancelButton = (Button) findViewById(R.id.cancelButtonItem);

        SharedPreferences SPdata = getSharedPreferences("sharedData", Context.MODE_PRIVATE);
        Budget_Name = SPdata.getString("currentBudgetName","Error");
        defaultHead.setText(Budget_Name);
        Budget_Main_Id = SPdata.getString("currentBudgetId","N/A");


        list = getResources().getStringArray(R.array.itemsType);
        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.typeList,list);
        spinner.setAdapter(arrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

               Name = list[position];
                Toast.makeText(AddItem.this,Name+"Selected",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddItemToDB();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SetDateTime()
    {
        Date = myDB.GetCurrentDate();
        Time = myDB.GetCurrentTime();
    }

    public void SelectOption(View view)
    {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Type = radioButton.getText().toString();
        Toast.makeText(AddItem.this,Type+"Selected",Toast.LENGTH_LONG).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void AddItemToDB()
    {
        Amount = amountEditText.getText().toString();
        int findAmount = Integer.parseInt(Amount);
        Description = descEditText.getText().toString();
        SetDateTime();
        boolean rs = myDB.InsertItem(Name,Description,Date,Time,Type,findAmount,Budget_Main_Id);
        if (rs)
        {
            Toast.makeText(AddItem.this,"Item Inserted Successfully",Toast.LENGTH_LONG).show();
            finish();
        }else
            {
                Toast.makeText(AddItem.this,"Item Failed Re Insert",Toast.LENGTH_LONG).show();
            }

    }
}
