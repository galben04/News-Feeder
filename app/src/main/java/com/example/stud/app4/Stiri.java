package com.example.stud.app4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Stiri extends AppCompatActivity {
    ListView stiriListView;
    ArrayList<Stire> stiriArrayList = new ArrayList<Stire>();
    StireArrayAdapter adapter;
    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stiri);

        stiriListView = (ListView) findViewById(R.id.stiriListView);


//        ArrayList<Stire> stiriArrayList = new ArrayList<Stire>();
//        stiriArrayList.add(new Stire("Ramai prost! Ce a facut Bianca Draguseanu!",
//                "Stire text ........dnsinindisninfidnfidnfidnifdnifnidnfidnifnidfidifnidnfindiniinifdnifndaifdnifain", "www.protv.ro"));
//        stiriArrayList.add(new Stire("Romania va fi condusa de tehnocrati",
//                "Stire text ........dnsinindisninfidnfidnfidnifdnifnidnfidnifnidfidifnidnfindiniinifdnifndaifdnifain", "www.adevarul.ro"));
//        stiriArrayList.add(new Stire("Romania va fi condusa de tehnocrati",
//                "Stire text ........dnsinindisninfidnfidnfidnifdnifnidnfidnifnidfidifnidnfindiniinifdnifndaifdnifain", "www.adevarul.ro"));

        stiriArrayList.add(new Stire("test", "test"));

        try {
            adapter = new StireArrayAdapter(getApplicationContext(),
                    R.layout.activity_listview_item, stiriArrayList);
            stiriListView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        URL = getIntent().getStringExtra("URL_SURSA");
        if (URL == null) {
            ///fa ceva
        }

        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.INTERNET);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getApplicationContext(),
                    Manifest.permission.INTERNET)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                ActivityCompat.requestPermissions((Activity) getApplicationContext(),
                        new String[]{Manifest.permission.INTERNET},
                        113);
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions((Activity) getApplicationContext(),
                        new String[]{Manifest.permission.INTERNET},
                        113);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            new RssDataController() {
                @Override
                protected void onPostExecute(ArrayList<Stire> result) {
                    adapter.updateAdapter(result);
                }
            }.execute(URL, "2");
        }

    }

//    public void updateAdapter(ArrayList<Stire> result) {
//        adapter.clear();
//        adapter.addAll(result);
//
//        adapter.notifyDataSetChanged();
//    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 113: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    new RssDataController() {
                        @Override
                        protected void onPostExecute(ArrayList<Stire> result) {
                            adapter.updateAdapter(result);
                        }
                    }.execute(URL);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int opt = item.getItemId();

        if (opt == R.id.opt_menu_add_site || opt == R.id.opt_menu_more_add_site) {
            Intent intent = new Intent(getApplicationContext(), NewWebsite.class);
            startActivity(intent);

        } else if (opt == R.id.opt_menu_about) {
            Intent intent = new Intent(getApplicationContext(), AboutUs.class);
            startActivity(intent);

        } else if (opt == R.id.opt_menu_surse) {
            Intent intent = new Intent(getApplicationContext(), Surse.class);
            startActivity(intent);

        } else if (opt == R.id.opt_menu_close) {
            this.finish();
        }

        return true;
    }


    static class ViewHolder {
        TextView titlu;
        TextView text;
        ImageButton gotoBtn;

        public ViewHolder() {
            super();
        }
    }

}