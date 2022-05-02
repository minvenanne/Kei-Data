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

public class CustomAdapterDevicesPrivate extends BaseAdapter {
    Context context;
    ArrayList<String> arraylist;
    ArrayList<Integer> icon;
    ImageButton delete;

    public CustomAdapterDevicesPrivate(Context context, ArrayList arraylist, ArrayList icon, ImageButton delete) {
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
            view = inflater.inflate(R.layout.activity_listview_devices, null);
        }
        TextView device = (TextView) view.findViewById(R.id.textView);
        ImageView icon_device = (ImageView) view.findViewById(R.id.icon);
        System.out.println(User.deviceList.get(i).deviceName);
        device.setText(User.deviceList.get(i).deviceName);
        icon_device.setImageResource(icon.get(i));
        ImageButton delete = (ImageButton) view.findViewById(R.id.list_view_trashcan);
        delete.setImageResource(R.drawable.trashcan);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                int position = listView.getPositionForView(parentRow);
                Device.removeDevicePrivate(position);
                System.out.println("I am in position " + position);
            }
        });
        return view;

    }
}
