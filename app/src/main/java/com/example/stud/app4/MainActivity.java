package com.example.stud.app4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button butMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        }

        return true;
    }

}
