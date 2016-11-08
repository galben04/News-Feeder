package com.example.stud.app4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.stud.app4.Preferences.AppPrefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Surse extends AppCompatActivity {
    private ListView surseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surse);

        surseListView = (ListView) findViewById(R.id.surse_listview);

        refreshList();
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }


    public void refreshList(){
        SharedPreferences sharedPrefs = getSharedPreferences(AppPrefs.PREFS_SURSE_FILENAME, MODE_PRIVATE);
        Set<String> surseSet = sharedPrefs.getStringSet(AppPrefs.PREFS_SURSE, null);

        List<String> listSurse = new ArrayList<String>(surseSet);

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                R.layout.acativity_listview, listSurse);

        surseListView.setAdapter(adapter);
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
}
