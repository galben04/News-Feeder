package com.example.stud.app4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by CaNou on 11/13/2016.
 */

public class StireArrayAdapter extends ArrayAdapter<Stire> {
    private ArrayList<Stire> list;

    Activity context;
    //this custom stireArrayAdapter receives an ArrayList of Stire objects.
    //Stire is my class that represents the data for a single row and could be anything.
    public StireArrayAdapter(Context context, int textViewResourceId, ArrayList<Stire> StireList)
    {
        //populate the local list with data.
        super(context, textViewResourceId, StireList);
        this.list = new ArrayList<Stire>();
        this.list.addAll(StireList);
        this.context = (Activity) context;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //creating the ViewHolder we defined earlier.
        final Stiri.ViewHolder holder = new Stiri.ViewHolder();

        //creating LayoutInflator for inflating the row layout.
        LayoutInflater inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflating the row layout we defined earlier.
        convertView = inflator.inflate(R.layout.new_stire_layout, null);

        //setting the views into the ViewHolder.
        holder.titlu = (TextView) convertView.findViewById(R.id.titleTextView);
        holder.text = (TextView) convertView.findViewById(R.id.detailsTextView);

        holder.gotoBtn = (ImageButton)  convertView.findViewById(R.id.btnGoToSursa);
        holder.detailsBtn = (ImageButton) convertView.findViewById(R.id.btnDetaliiStire);

        holder.wrapperGoToBtn = (LinearLayout) convertView.findViewById(R.id.wrapperDetails);
        holder.wrapperDetailsBtn = (LinearLayout) convertView.findViewById(R.id.wrapperDetails);

        holder.detailLinearLayout = (LinearLayout) convertView.findViewById(R.id.detailsLinearLayout);

        //define an onClickListener for the ImageView.
        holder.wrapperGoToBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(), "Loading article from orignal website...", Toast.LENGTH_SHORT).show();
                //foloseste list.get(position).url;
                Intent intent = new Intent(context, WebBrowserStire.class);
                intent.putExtra("URL_SURSA", list.get(position).urlSursa);
                intent.putExtra("TEST_ARTICOL", list.get(position).text);//http://www.tested.com/podcast-xml/");

                context.startActivity(intent);
            }
        });

        holder.wrapperDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.detailLinearLayout.getVisibility() == View.GONE) {
                    holder.detailsBtn.setBackgroundResource(R.mipmap.up_arrow);
                    holder.detailLinearLayout.setVisibility(View.VISIBLE);
                }else{
                    holder.detailsBtn.setBackgroundResource(R.mipmap.down_arrow);
                    holder.detailLinearLayout.setVisibility(View.GONE);
                }
            }
        });

        //setting data into the the ViewHolder.
        holder.titlu.setText(list.get(position).titlu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.text.setText(Html.fromHtml(list.get(position).text, Html.FROM_HTML_MODE_LEGACY));
        } else
            holder.text.setText(Html.fromHtml(list.get(position).text));
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

    public void updateAdapter(ArrayList<Stire> result) {
        this.clear();
        this.addAll(result);

        this.notifyDataSetChanged();
    }

    public void AddAndupdateAdapter(ArrayList<Stire> result) {
        result.addAll(this.list);
        this.clear();
        this.addAll(result);

        this.notifyDataSetChanged();
    }

}

