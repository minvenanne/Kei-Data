package com.example.kei_data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    public CustomAdapterUsers(Context context, ArrayList arraylist, ImageView icon, ImageButton delete) {
        this.context = context;
        this.arraylist = arraylist;
        this.icon = icon;
        this.delete = delete;
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
        TextView device = (TextView) view.findViewById(R.id.textView);
        ImageView icon_user = (ImageView) view.findViewById(R.id.icon2);
        device.setText(arraylist.get(i));
        icon_user.setImageResource(R.drawable.ic_baseline_person_24_large);

        edit = (ImageButton) view.findViewById(R.id.Edit_Button2);
        edit.setImageResource(R.drawable.ic_baseline_edit_24);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                int position = listView.getPositionForView(parentRow);
                System.out.println("I am in position " + position);
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
                Household.removeUser(position);
                System.out.println("I am in position " + position);
            }
        });
        return view;

    }
}
