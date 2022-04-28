package com.example.kei_data;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class User_activity extends AppCompatActivity{
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;
    public ArrayList<String> Users;
    public ListView simpleListviewUsers;
    public TextView Name;
    public String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_activity.this, Settings_activity1.class);
                startActivity(intent);
            }
        });

        homeButton = (ImageButton) findViewById(R.id.Total);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_activity.this, Categories_activity.class);
                startActivity(intent);
            }
        });
        ArrayList<String> Users = new ArrayList<>();

        Users.add("Mother Julie");
        Users.add("Father Dennis");
        Users.add("Little sister Laura");
        Users.add("Big brother Jacob");
        Users.add("Baby sister Ida");

        ImageButton delete = (ImageButton) findViewById(R.id.list_view_trashcan2);
        ImageView icon = (ImageView) findViewById(R.id.icon2);

        simpleListviewUsers = (ListView) findViewById(R.id.simpleListViewUsers);
        CustomAdapterUsers customAdapterUsers = new CustomAdapterUsers(getApplicationContext(), Users, icon, delete);
        simpleListviewUsers.setMinimumHeight(justifyListViewHeightBasedOnChildren (simpleListviewUsers,customAdapterUsers));
        simpleListviewUsers.setAdapter(customAdapterUsers);

        userName = "Mads Bo";
        Name = findViewById(R.id.userName);
        Name.setText(userName);
    }

    //https://stackoverflow.com/questions/12212890/disable-scrolling-of-a-listview-contained-within-a-scrollview/27818661#27818661

    private int justifyListViewHeightBasedOnChildren (ListView listView, CustomAdapterUsers customAdapterUsers) {

        CustomAdapterUsers adapter = customAdapterUsers;

        if (adapter == null) {
            return 0;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
        return totalHeight;
    }
}