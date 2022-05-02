package com.example.kei_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;

import android.content.Intent;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class User_activity extends AppCompatActivity{
    public ImageButton settingsButton;
    public ImageButton homeButton;
    public ImageButton categoriesButton;
    public ImageButton edit;
    public Button AddHouseholdMember;
    public static ListView simpleListviewUsers;
    public TextView Name;
    public static CustomAdapterUsers customAdapterUsers;
    public EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        if (Household.userList.size()==0){
            addElementsToArray();
        }

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

        edit = (ImageButton) findViewById(R.id.Edit_Button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = (EditText) findViewById(R.id.editName);
                editText.setVisibility(View.VISIBLE);
                editText.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (i == KeyEvent.KEYCODE_ENTER){
                            Household.setUserName(editText.getText().toString().trim());
                            editText.setVisibility(View.INVISIBLE);
                            Name.setText(Household.user);
                            closeKeyboard();
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

        AddHouseholdMember = (Button) findViewById(R.id.Add_Household_Members);
        AddHouseholdMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_activity.this, AddUser_activity.class);
                startActivity(intent);
            }
        });

        ImageButton delete = (ImageButton) findViewById(R.id.list_view_trashcan2);
        ImageView icon = (ImageView) findViewById(R.id.icon2);

        simpleListviewUsers = (ListView) findViewById(R.id.simpleListViewUsers);
        customAdapterUsers = new CustomAdapterUsers(getApplicationContext(), Household.userList, icon, delete);
        simpleListviewUsers.setMinimumHeight(justifyListViewHeightBasedOnChildren (simpleListviewUsers,customAdapterUsers));
        simpleListviewUsers.setAdapter(customAdapterUsers);

        Name = findViewById(R.id.userName);
        Name.setText(Household.user);
    }

    //https://stackoverflow.com/questions/12212890/disable-scrolling-of-a-listview-contained-within-a-scrollview/27818661#27818661
    public static int justifyListViewHeightBasedOnChildren (ListView listView, CustomAdapterUsers customAdapterUsers) {

        if (customAdapterUsers == null) {
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0; i < customAdapterUsers.getCount(); i++) {
            View listItem = customAdapterUsers.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (customAdapterUsers.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
        return totalHeight;
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

    private void addElementsToArray(){
        Household.addUser("Mother Julie");
        Household.addUser("Father Dennis");
        Household.addUser("Little sister Laura");
        Household.addUser("Big brother Jacob");
        Household.addUser("Baby sister Ida");
    }
}