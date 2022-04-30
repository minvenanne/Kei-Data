package com.example.kei_data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.jar.Attributes;

public class AddUser_activity extends AppCompatActivity {
    ImageButton settingsButton;
    ImageButton homeButton;
    ImageButton categoriesButton;
    EditText UserName;
    EditText UserId;
    static String NameofHouseholdMember;
    int IdofHouseholdMember = -1;
    Button AddUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        settingsButton = (ImageButton) findViewById(R.id.Settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUser_activity.this, Settings_activity1.class);
                startActivity(intent);
            }
        });

        homeButton = (ImageButton) findViewById(R.id.Total);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUser_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        categoriesButton = (ImageButton) findViewById(R.id.Categories);
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUser_activity.this, Categories_activity.class);
                startActivity(intent);
            }
        });

        UserName = (EditText) findViewById(R.id.Name);
        UserName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER){
                    NameofHouseholdMember = UserName.getText().toString().trim();
                    closeKeyboard();
                    enableButton();
                    return true;
                }
                return false;
            }
        });

        UserId = (EditText) findViewById(R.id.UserId);
        UserId.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER){
                    IdofHouseholdMember = Integer.parseInt(UserId.getText().toString().trim());
                    closeKeyboard();
                    enableButton();
                    return true;
                }
                return false;
            }
        });

        AddUser = (Button) findViewById(R.id.AddUser);
        AddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User_activity.addUser(NameofHouseholdMember);
                Intent intent = new Intent(AddUser_activity.this, User_activity.class);
                startActivity(intent);
            }
        });


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
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

    private void enableButton(){
        if (IdofHouseholdMember != -1 && NameofHouseholdMember != null){
            AddUser.setEnabled(true);
        }
        else{
            AddUser.setEnabled(false);
        }
    }
}
