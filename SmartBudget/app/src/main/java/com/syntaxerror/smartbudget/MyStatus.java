package com.syntaxerror.smartbudget;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MyStatus extends AppCompatActivity {
    private ImageView backImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystatus);

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
