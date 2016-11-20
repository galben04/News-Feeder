package com.example.stud.app4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stud.app4.Preferences.AppPrefs;
import com.google.gson.Gson;

import static com.example.stud.app4.Preferences.AppPrefs.PREFS_FILENAME;

public class Surse extends AppCompatActivity {
    private ListView surseListView;
    SurseArrayList surseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surse);


        surseListView = (ListView) findViewById(R.id.surse_listview);
        refreshList();


        surseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Stiri.class);
                intent.putExtra("URL_SURSA", surseArrayList.list.get(position).url); //http://www.tested.com/podcast-xml/");
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }


    public void refreshList () {
//        SharedPreferences sharedPrefs = getSharedPreferences(AppPrefs.PREFS_FILENAME, MODE_PRIVATE);
//        Set<String> surseSet = sharedPrefs.getStringSet(AppPrefs.PREFS_SURSE, null);
//
//        //List<String> listSurse = new ArrayList<String>(surseSet);
//
//        ArrayList<Site> listData = new ArrayList<Site>();
//        listData.add(new Site("9gag", "www.9gag.com"));
//        listData.add(new Site("Google", "www.gogle.com"));
//        listData.add(new Site("Adevarul", "www.adevarul.ro"));
//
//
        SharedPreferences mPrefs = getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json1 = mPrefs.getString(AppPrefs.PREFS_SURSE, "");
        surseArrayList = gson.fromJson(json1, SurseArrayList.class);

        try {
            SurseArrayAdapter adapter = new SurseArrayAdapter(getApplicationContext(),
                    R.layout.activity_listview_item, surseArrayList.list);
            surseListView.setAdapter(adapter);
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
            refreshList();

        }else if(opt == R.id.opt_menu_close) {
            this.finish();
        }

        return true;
    }

    static class ViewHolder{
        TextView titlu;
        TextView url;
    }
}
