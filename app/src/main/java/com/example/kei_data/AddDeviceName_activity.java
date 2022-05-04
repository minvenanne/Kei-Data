package com.example.kei_data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevicename);
        User mainUser = (User) getIntent().getSerializableExtra("user");

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
        editTextName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER){
                    deviceName = editTextName.getText().toString().trim();
                    add_device.setEnabled(true);
                    closeKeyboard();
                    return true;
                }
                return false;
            }
        });

        add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceName_activity.this, DeviceSuccess_activity.class);
                startActivity(intent);
                mainUser.addDevice(AddDeviceType_activity.getDeviceType(), AddDeviceIP_activity.getDeviceIp(), getDeviceName(), mainUser);
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
        System.out.println(deviceName);
        return deviceName;
    }

    private void closeKeyboard()
    {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = this.getCurrentFocus();

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
