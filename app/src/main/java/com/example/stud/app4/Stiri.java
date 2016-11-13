package com.example.stud.app4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Stiri extends AppCompatActivity {
    ListView stiriListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stiri);

        stiriListView = (ListView) findViewById(R.id.stiriListView);

        ArrayList<Stire> stiriArrayList = new ArrayList<Stire>();
        stiriArrayList.add(new Stire("Ramai prost! Ce a facut Bianca Draguseanu!",
                "Stire text ........dnsinindisninfidnfidnfidnifdnifnidnfidnifnidfidifnidnfindiniinifdnifndaifdnifain", "www.protv.ro"));
        stiriArrayList.add(new Stire("Romania va fi condusa de tehnocrati",
                "Stire text ........dnsinindisninfidnfidnfidnifdnifnidnfidnifnidfidifnidnfindiniinifdnifndaifdnifain", "www.adevarul.ro"));
        stiriArrayList.add(new Stire("Romania va fi condusa de tehnocrati",
                "Stire text ........dnsinindisninfidnfidnfidnifdnifnidnfidnifnidfidifnidnfindiniinifdnifndaifdnifain", "www.adevarul.ro"));
        try {
            StireArrayAdapter adapter = new StireArrayAdapter(getApplicationContext(),
                    R.layout.activity_listview_item, stiriArrayList);
            stiriListView.setAdapter(adapter);
        } catch(Exception e){
            e.printStackTrace();
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
            Intent intent = new Intent(getApplicationContext(), Surse.class);
            startActivity(intent);

        }else if(opt == R.id.opt_menu_close) {
            this.finish();
        }

        return true;
    }
    static class ViewHolder{
        TextView titlu;
        TextView text;
        ImageButton gotoBtn;
    }
}

