package com.example.kei_data;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class AddDeviceType_activity extends AppCompatActivity {

    public Button next;
    public Button back;
    public RadioGroup type;
    public static String type_device;
    private static int checkedButton;
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevicetype);
        User mainUser = (User) getIntent().getSerializableExtra("user");

        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceType_activity.this, Settings_activity1.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        homeButton = (ImageButton) findViewById(R.id.Total);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceType_activity.this, MainActivity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceType_activity.this, Categories_activity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

        RadioGroup type = (RadioGroup) findViewById(R.id.radioGroup1);
        next = (Button) findViewById(R.id.Next);

        type.check(checkedButton);
        if (checkedButton != -1){
            enableNext();
        }

        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedButton = checkedId;
                if (checkedId == R.id.Computer){
                    type_device = "Computer";
                }
                else if (checkedId == R.id.Phone){
                    type_device = "Phone";
                }
                else if (checkedId == R.id.Speaker){
                    type_device = "Speaker";
                }
                else if (checkedId == R.id.TV){
                    type_device = "TV";
                }
                else if (checkedId == R.id.Other){
                    type_device = "Other";
                }
                enableNext();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceType_activity.this, AddDeviceIP_activity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", mainUser);
                startActivity(intent);
            }
        });

        back = (Button) findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceType_activity.this, Devices_activity.class);
                intent.putExtra("household", getIntent().getSerializableExtra("household"));
                intent.putExtra("user", getIntent().getSerializableExtra("user"));
                startActivity(intent);
            }
        });

    }
    public static String getDeviceType(){
        return type_device;
    }

    public static void setCheckedButton(){
        checkedButton = -1;
    }

    public void enableNext(){
        next.setEnabled(true);
    }

}
