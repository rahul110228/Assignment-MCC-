package com.example.rahuldebmohalder.problem1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



/**
 * Created by Rahul Deb Mohalder on 17-01-2018.
 */

public class ChooseActivity  extends AppCompatActivity {

    Button btnImage, btnInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_option);



        componentInisialize();


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(ChooseActivity.this, ImageShowfromWebActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(ChooseActivity.this, InfoViewActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });



    }

    public void componentInisialize(){
        btnImage = (Button) findViewById(R.id.btnwebImage);
        btnInfo = (Button) findViewById(R.id.btnViewInfo);

    }
}

