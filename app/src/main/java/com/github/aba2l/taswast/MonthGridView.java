package com.github.aba2l.taswast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MonthGridView extends BaseAdapter {

    private Context mContext;
    private final String[] gridViewString;
    private final int[] dayGridViewImageId;
    private final int[] eventGridViewImageId;

    public MonthGridView(Context context, String[] gridViewString, int[] dayGridViewImageId, int[] eventGridViewImageId) {
        mContext = context;
        this.dayGridViewImageId = dayGridViewImageId;
        this.eventGridViewImageId = eventGridViewImageId;
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
            gridViewAndroid = inflater.inflate(R.layout.month_grid_view, null);
            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
            ImageView dayImageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image_day);
            ImageView eventImageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image_event);
            textViewAndroid.setText(gridViewString[i]);
            dayImageViewAndroid.setImageResource(dayGridViewImageId[i]);
            eventImageViewAndroid.setImageResource(eventGridViewImageId[i]);
        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }
}