package com.example.kei_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class AddDeviceName_activity extends AppCompatActivity {
    Button add_device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevicename);

        add_device = (Button) findViewById(R.id.add_device);
        add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceName_activity.this, Devices_activity.class);
                startActivity(intent);
            }
        });
    }
}
