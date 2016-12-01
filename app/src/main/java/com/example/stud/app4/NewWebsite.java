package com.example.stud.app4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewWebsite extends AppCompatActivity {
    Button addSite;
    TextView titleTextEdit, urlTextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_website);

        urlTextEdit = (EditText) findViewById(R.id.editTextURL);
        titleTextEdit = (EditText) findViewById(R.id.editTextTilu);

        if(getIntent().getStringExtra(Utils.TITLU_SURSA) != null)
            urlTextEdit.setText(getIntent().getStringExtra(Utils.TITLU_SURSA));

        if(getIntent().getStringExtra(Utils.URL_SURSA) != null)
            urlTextEdit.setText(getIntent().getStringExtra(Utils.URL_SURSA));

        addSite = (Button) findViewById(R.id.addSite);
        addSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    SharedPreferences mPrefs = getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE);
//                    Gson gson = new Gson();
//                    String json = mPrefs.getString(AppPrefs.PREFS_SURSE, "");
//
//                    SurseArrayList surseArrayList = gson.fromJson(json, SurseArrayList.class);
//
//                    if(surseArrayList == null)
//                        surseArrayList = new SurseArrayList();
//
//                   SharedPreferences sharedPrefs = getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE);
//
//                    Set<String> surseSet = sharedPrefs.getStringSet(AppPrefs.PREFS_SURSE, new HashSet<String>());
//
                    if(urlTextEdit.getText().toString().length() != 0) {
                        Site actualSite;
                        if(titleTextEdit.getText().toString().length() != 0)
                            actualSite = new Site(titleTextEdit.getText().toString(), urlTextEdit.getText().toString());
                        else
                            actualSite = new Site(urlTextEdit.getText().toString());

                        Utils.surseArrayList.list.add(actualSite);
                        Utils.hasChangedSurse = true;

                    }else
                        throw new Exception("The url is null");


//                    SharedPreferences.Editor editor = sharedPrefs.edit();
//                    editor.clear();
//                    editor.putStringSet(PREFS_SURSE , surseSet);
//                    editor.commit();
//                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
//                    gson = new Gson();
//                    String json1 = gson.toJson(surseArrayList);
//
//                    prefsEditor.clear();
//                    prefsEditor.putString(AppPrefs.PREFS_SURSE, json1);
//                    prefsEditor.apply();

                    if(titleTextEdit.getText().toString().length() > 0)
                        Toast.makeText(getApplicationContext(), titleTextEdit.getText().toString()
                                +" "+ getResources().getString(R.string.new_website_added), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), urlTextEdit.getText().toString()
                                +" "+ getString(R.string.new_website_added), Toast.LENGTH_SHORT).show();

                    titleTextEdit.setText("");
                    urlTextEdit.setText("");

                    finish();

                } catch(Exception e){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.new_website_error) + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
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
//            Intent intent = new Intent(getApplicationContext(), NewWebsite.class);
//            startActivity(intent);

        }else if(opt == R.id.opt_menu_about) {
            Intent intent = new Intent(getApplicationContext(), AboutUs.class);
            startActivity(intent);

        }else if(opt == R.id.opt_menu_surse) {
            Intent intent = new Intent(getApplicationContext(), Surse.class);
            startActivity(intent);

        }else if(opt == R.id.opt_menu_surse) {
            this.finish();
        }

        return true;
    }
}

