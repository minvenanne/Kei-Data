package com.example.kei_data;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterUsers extends BaseAdapter {
    Context context;
    ArrayList<String> arraylist;
    ImageView icon;
    ImageButton delete;
    ImageButton edit;
    Household testHousehold;

    public CustomAdapterUsers(Context context, ImageView icon, ImageButton delete, ArrayList temp, Household testHousehold) {
        this.context = context;
        this.icon = icon;
        this.delete = delete;
        this.arraylist = temp;
        this.testHousehold = testHousehold;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_listview_household, null);
        }

        TextView user = (TextView) view.findViewById(R.id.displayName);
        ImageView icon_user = (ImageView) view.findViewById(R.id.icon2);
        user.setText(arraylist.get(i));
        icon_user.setImageResource(R.drawable.ic_baseline_person_40_large);

        edit = (ImageButton) view.findViewById(R.id.Edit_Button2);
        edit.setImageResource(R.drawable.ic_baseline_edit_24);

        EditText changeName = (EditText) view.findViewById(R.id.editNameHouseholdmember);
        changeName.setWidth(0);
        user.setWidth(800);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                int position = listView.getPositionForView(parentRow);
                System.out.println("I am in position " + position);
                user.setVisibility(View.INVISIBLE);
                user.setWidth(0);
                user.setHeight(0);
                changeName.setWidth(800);
                changeName.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        if (i == KeyEvent.KEYCODE_ENTER){
                            testHousehold.userList.get(position+1).setUserName(changeName.getText().toString().trim());
                            changeName.setWidth(0);
                            user.setWidth(800);
                            user.setHeight(160);
                            user.setVisibility(View.VISIBLE);
                            user.setText(changeName.getText().toString().trim());
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

        delete = (ImageButton) view.findViewById(R.id.list_view_trashcan2);
        delete.setImageResource(R.drawable.trashcan);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                int position = listView.getPositionForView(parentRow);
                testHousehold.removeUser(position + 1);
                arraylist.remove(position);
                System.out.println("I am in position " + position);
                User_activity.customAdapterUsers.notifyDataSetChanged();
            }
        });
        return view;


    }

}
