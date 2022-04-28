package com.example.kei_data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;

import android.content.Intent;

import android.view.View;
import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.CompoundButton;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
   // public Button button;
    public ImageButton settingsButton;
    public ImageButton categoriesButton;

    private RadioGroup radioGroupPhoto;
    private ImageView imageViewPhoto;
    private int []photos = {R.drawable.d, R.drawable.graph2, R.drawable.m, R.drawable.six_m,R.drawable.y};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* // her tester vi kode
        User user = new User();
        user.currentDataUseStandpoint = (float) 0;
        user.currentCo2 = (float) 0;
        user.updateCurrentDataUseStandpointAndCo2();
        System.out.println(user.userName);
        //*/ // her tester vi kode

        setContentView(R.layout.activity_main);

        this.imageViewPhoto = (ImageView) findViewById(R.id.imageView);
        this.radioGroupPhoto = (RadioGroup) findViewById(R.id.radioGroup);
        this.radioGroupPhoto.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                imageViewPhoto.setImageResource(photos[index]);
            }
        });

        Switch householdSwitch = findViewById(R.id.household);
//            Lets the user switch between two modes.
        householdSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (householdSwitch.isChecked()) {
                    Log.d("Success", "Household");
                    Intent intent = new Intent(MainActivity.this, Household_activity.class);
                    startActivity(intent);

                } else {
                    Log.d("Success", "you");
                }
            }
        });
        //END// SWITCH BETWEEN YOU AND HOUSEHOLD

        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings_activity1.class);
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Categories_activity.class);
                startActivity(intent);
            }
        });


    }

}