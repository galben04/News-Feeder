package com.example.stud.app4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Surse extends AppCompatActivity {
    private ListView surseListView;
    SurseArrayList surseArrayList;
    SurseArrayAdapter adapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surse);
        mContext = this;

        surseListView = (ListView) findViewById(R.id.surse_listview);
        surseListView.setLongClickable(true);


        try {
            adapter = new SurseArrayAdapter(this,
                    R.layout.activity_listview_item, Utils.surseArrayList.list);
            surseListView.setAdapter(adapter);
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        surseListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int pos, long id) {

                final Site actualSite = Utils.surseArrayList.list.get(pos);

                final String[] items = {"Sterge", "Editeaza"};
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                //builder.setTitle("Make your selection");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Utils.surseArrayList.list.remove(actualSite);
                                Utils.hasChangedSurse = true;
                                dialog.dismiss();
                                break;
//                            case 1:
//                                break;
                            default:
                                dialog.dismiss();
                        }
                    }
                });
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        adapter.updateAdapter(Utils.surseArrayList.list);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

                Log.v("long clicked","pos: " + pos);

                return true;
            }
        });

        if(Utils.hasChangedSurse)
            refreshList();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void refreshList () {
        if(adapter != null)
            adapter.updateAdapter(Utils.surseArrayList.list);
        else {
            try {
                adapter = new SurseArrayAdapter(this,
                        R.layout.activity_listview_item, Utils.surseArrayList.list);
                surseListView.setAdapter(adapter);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        int opt = item.getItemId();

        if(opt == R.id.opt_menu_add_site || opt == R.id.opt_menu_more_add_site){
            Intent intent = new Intent(getApplicationContext(), NewWebsite.class);
            startActivity(intent);

        }else if(opt == R.id.opt_menu_about) {
            Intent intent = new Intent(getApplicationContext(), AboutUs.class);
            startActivity(intent);

        }else if(opt == R.id.opt_menu_surse) {
            refreshList();

        }else if(opt == R.id.opt_menu_close) {
            this.finish();
        }

        return true;
    }

    static class ViewHolder{
        TextView titlu;
        TextView url;
        ImageButton fav;
        LinearLayout infLinearLayout;
    }
}
