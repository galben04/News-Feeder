package com.example.stud.app4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CaNou on 11/12/2016.
 */

public class SurseArrayAdapter extends ArrayAdapter<Site>
{
    private ArrayList<Site> list;
    private Activity context;
    int position;

    //this custom stireArrayAdapter receives an ArrayList of Site objects.
    //Site is my class that represents the data for a single row and could be anything.
    public SurseArrayAdapter(Activity context, int textViewResourceId, ArrayList<Site> SiteList)
    {
        //populate the local list with data.
        super(context, textViewResourceId, SiteList);
        this.list = new ArrayList<Site>();
        this.list.addAll(SiteList);
        this.context = context;
    }

    public View getView(final int position, View convertView, final ViewGroup parent)
    {
        //creating the ViewHolder we defined earlier.
        final Surse.ViewHolder holder = new Surse.ViewHolder();

        //creating LayoutInflator for inflating the row layout.
        LayoutInflater inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the row layout we defined earlier.
        convertView = inflator.inflate(R.layout.activity_listview_item, null);

        //setting the views into the ViewHolder.
        holder.titlu = (TextView) convertView.findViewById(R.id.titleTextViewItem);
        holder.url = (TextView) convertView.findViewById(R.id.urlTextViewItem);
        holder.fav = (ImageButton) convertView.findViewById(R.id.favImgBtn);
        holder.infLinearLayout = (LinearLayout) convertView.findViewById(R.id.infWrapperLinearLayout);

        //define an onClickListener for the ImageView.
        holder.infLinearLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, Stiri.class);
                intent.putExtra("URL_SURSA", list.get(position).url); //http://www.tested.com/podcast-xml/");

                context.startActivity(intent);
                //Toast.makeText(getContext(), "Image from row " + position + " was pressed", Toast.LENGTH_LONG).show();
            }
        });

        final View convertViewFinal = convertView;

        holder.infLinearLayout.setOnLongClickListener(new CustomOnLongClickListener(position, this));

        holder.fav.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(list.get(position).isPref == true) {
                    list.get(position).isPref = false;
                    holder.fav.setBackgroundResource(R.mipmap.star_off);
                }else{
                    list.get(position).isPref = true;
                    holder.fav.setBackgroundResource(R.mipmap.star_on);
                }
                Utils.hasChangedSurse = true;

                //Toast.makeText(getContext(), "Image from row " + position + " was pressed", Toast.LENGTH_LONG).show();
            }
        });

        //setting data into the the ViewHolder.
        holder.titlu.setText(list.get(position).titlu);
        holder.url.setText(list.get(position).url);

        if(list.get(position).isPref == true)
            holder.fav.setBackgroundResource(R.mipmap.star_on);
        else
            holder.fav.setBackgroundResource(R.mipmap.star_off);

        //return the row view.
        return convertView;
    }

    @Override
    public void add(Site object) {
        list.add(object);
    }

    @Override
    public void clear() {
        list.clear();
    }

    public void addAll(ArrayList<Site> items) {
        list.addAll(items);
    }

    @Override
    public Site getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        //super.getCount();
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateAdapter(ArrayList<Site> result) {
        this.list.clear();
        this.list.addAll(result);

        this.notifyDataSetChanged();
    }

    public void updateAdapter() {
        this.notifyDataSetChanged();
    }

}