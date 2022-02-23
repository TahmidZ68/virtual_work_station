package com.example.virtual_work_station;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventListAdaptar extends ArrayAdapter<Event> {

    private LayoutInflater inflater;
    private int resourceId;

    public EventListAdaptar( Context context, int resource, List<Event> items) {
        super(context, resource, items);

        this.resourceId = resource;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View rowView = inflater.inflate(resourceId,parent,false);

        TextView ProjectName = rowView.findViewById(R.id.tvEventName);
        TextView Projectlocation = rowView.findViewById(R.id.tvPalceName);
        TextView date =rowView.findViewById(R.id.tvEventDateTime);

        Event e = this.getItem(position);

        ProjectName.setText(e.getTitle());
     //   Projectlocation.setText(e.getLocation());
        //date.setText(e.getDate());



        return rowView;
    }
}
