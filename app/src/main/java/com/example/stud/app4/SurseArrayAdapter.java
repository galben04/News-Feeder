package com.example.stud.app4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by CaNou on 11/12/2016.
 */

public class SurseArrayAdapter extends ArrayAdapter<Site>
{
    private ArrayList<Site> list;

    //this custom adapter receives an ArrayList of Site objects.
    //Site is my class that represents the data for a single row and could be anything.
    public SurseArrayAdapter(Context context, int textViewResourceId, ArrayList<Site> SiteList)
    {
        //populate the local list with data.
        super(context, textViewResourceId, SiteList);
        this.list = new ArrayList<Site>();
        this.list.addAll(SiteList);
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //creating the ViewHolder we defined earlier.
        Surse.ViewHolder holder = new Surse.ViewHolder();

        //creating LayoutInflator for inflating the row layout.
        LayoutInflater inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the row layout we defined earlier.
        convertView = inflator.inflate(R.layout.activity_listview_item, null);

        //setting the views into the ViewHolder.
        holder.titlu = (TextView) convertView.findViewById(R.id.titleTextViewItem);
        holder.url = (TextView) convertView.findViewById(R.id.urlTextViewItem);

        //define an onClickListener for the ImageView.
        holder.titlu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(), "Image from row " + position + " was pressed", Toast.LENGTH_LONG).show();
            }
        });


        //setting data into the the ViewHolder.
        holder.titlu.setText(list.get(position).titlu);
        holder.url.setText(list.get(position).url);

        //return the row view.
        return convertView;
    }
}