package com.example.stud.app4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by CaNou on 11/13/2016.
 */

public class StireArrayAdapter extends ArrayAdapter<Stire> {
    private ArrayList<Stire> list;
    //this custom adapter receives an ArrayList of Stire objects.
    //Stire is my class that represents the data for a single row and could be anything.
    public StireArrayAdapter(Context context, int textViewResourceId, ArrayList<Stire> StireList)
    {
        //populate the local list with data.
        super(context, textViewResourceId, StireList);
        this.list = new ArrayList<Stire>();
        this.list.addAll(StireList);
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //creating the ViewHolder we defined earlier.
        Stiri.ViewHolder holder = new Stiri.ViewHolder();

        //creating LayoutInflator for inflating the row layout.
        LayoutInflater inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the row layout we defined earlier.
        convertView = inflator.inflate(R.layout.stire_layout_item, null);

        //setting the views into the ViewHolder.
        holder.titlu = (TextView) convertView.findViewById(R.id.titluTextView);
        holder.text = (TextView) convertView.findViewById(R.id.textTextView);
        holder.gotoBtn = (ImageButton)  convertView.findViewById(R.id.goToSourceImgButton);

        //define an onClickListener for the ImageView.
        holder.gotoBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(), "Indisponibil...", Toast.LENGTH_LONG).show();
                //foloseste list.get(position).url;
            }
        });


        //setting data into the the ViewHolder.
        holder.titlu.setText(list.get(position).titlu);
        holder.text.setText(list.get(position).text);
        //holder.gotoBtn.setBackground();

        //return the row view.
        return convertView;
    }

    @Override
    public void add(Stire object) {
        list.add(object);
    }

    @Override
    public void clear() {
        list.clear();
    }

    public void addAll(ArrayList<Stire> items) {
        list.addAll(items);
    }

    @Override
    public Stire getItem(int position) {
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
}

