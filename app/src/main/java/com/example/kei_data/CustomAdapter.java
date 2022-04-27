package com.example.kei_data;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> arraylist;
    int icon[];
    ImageButton delete;

    public CustomAdapter(Context context, ArrayList arraylist, int icon[], ImageButton delete) {
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
            view = inflater.inflate(R.layout.activity_listview, null);
        }
        TextView device = (TextView) view.findViewById(R.id.textView);
        ImageView icon_device = (ImageView) view.findViewById(R.id.icon);
        device.setText(arraylist.get(i));
        icon_device.setImageResource(icon[i]);
        ImageButton delete2 = (ImageButton) view.findViewById(R.id.list_view_trashcan);
        delete2.setImageResource(R.drawable.trashcan);
        return view;

    }
}