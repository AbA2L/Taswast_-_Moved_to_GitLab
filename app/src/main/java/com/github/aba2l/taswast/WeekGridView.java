package com.github.aba2l.taswast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeekGridView extends BaseAdapter {

    private Context mContext;
    private final String[] gridViewString;

    public WeekGridView(Context context, String[] gridViewString) {
        mContext = context;
        this.gridViewString = gridViewString;
    }

    @Override
    public int getCount() {
        return gridViewString.length;
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
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.week_grid_view, null);
            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
            if (i<5){
                textViewAndroid.setText(gridViewString[i]);
            } else {
                textViewAndroid.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                textViewAndroid.setText(gridViewString[i]);
            }

        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }
}