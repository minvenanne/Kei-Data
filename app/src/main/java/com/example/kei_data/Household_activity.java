package com.example.kei_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class Household_activity extends AppCompatActivity {

    public ImageButton settingsButton;
    public ImageButton categoriesButton;


    private RadioGroup radioGroupPhoto;
    private ImageView imageViewPhoto;
    private int []photos = {R.drawable.d, R.drawable.graph2, R.drawable.m, R.drawable.six_m,R.drawable.y};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);

        this.imageViewPhoto = (ImageView) findViewById(R.id.householdImageView);
        this.radioGroupPhoto = (RadioGroup) findViewById(R.id.householdRadioGroup);
        this.radioGroupPhoto.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                imageViewPhoto.setImageResource(photos[index]);
            }
        });

        Switch householdSwitch = findViewById(R.id.householdHousehold);
//            Lets the user switch between two modes.
        householdSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (householdSwitch.isChecked()) {
                    Log.d("Success", "Household");


                } else {
                    Log.d("Success", "you");
                    Intent intent = new Intent(Household_activity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        //END// SWITCH BETWEEN YOU AND HOUSEHOLD

        settingsButton = (ImageButton) findViewById(R.id.householdSettings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Household_activity.this, Settings_activity1.class);
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.householdCategories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Household_activity.this, Categories_activity.class);
                startActivity(intent);
            }
        });
    }
}