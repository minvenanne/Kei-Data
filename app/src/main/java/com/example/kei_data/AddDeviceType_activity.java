package com.example.kei_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class AddDeviceType_activity extends AppCompatActivity {

    public Button next;
    public Button back;
    public RadioGroup type;
    public String type_device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevicetype);

        RadioGroup type = (RadioGroup) findViewById(R.id.radioGroup1);
        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == 2131230730){
                    type_device = "Computer";
                }
                else if (checkedId == 2131230740){
                    type_device = "Phone";
                }
                else if (checkedId == 2131230747){
                    type_device = "Speaker";
                }
                else if (checkedId == 2131230750){
                    type_device = "TV";
                }
                else if (checkedId == 2131230739){
                    type_device = "Other";
                }
                next.setEnabled(true);
                System.out.println(type_device);
            }
        });

        next = (Button) findViewById(R.id.Next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceType_activity.this, AddDeviceIP_activity.class);
                startActivity(intent);
            }
        });

        back = (Button) findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDeviceType_activity.this, Devices_activity.class);
                startActivity(intent);
            }
        });

    }
}
