package com.example.kei_data;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;


public class AddDeviceName_activity extends AppCompatActivity {
    Button add_device;
    EditText editTextName;
    public static String deviceName;
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;
    private
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevicename);

        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceName_activity.this, Settings_activity1.class);
                startActivity(intent);
            }
        });

        homeButton = (ImageButton) findViewById(R.id.Total);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceName_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceName_activity.this, Categories_activity.class);
                startActivity(intent);
            }
        });

        add_device = (Button) findViewById(R.id.add_device);
        editTextName = findViewById(R.id.Name);

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String nameInput = editTextName.getText().toString().trim();
                add_device.setEnabled(!nameInput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceName_activity.this, DeviceSuccess_activity.class);
                startActivity(intent);
                deviceName = editTextName.getText().toString();
                System.out.println(deviceName);
                Devices_activity.add_devicePrivate();
            }
        });

        back = (Button) findViewById(R.id.Back3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceName_activity.this, AddDeviceIP_activity.class);
                startActivity(intent);
            }
        });
    }

    public static String getDeviceName(){
        return deviceName;
    }


}
