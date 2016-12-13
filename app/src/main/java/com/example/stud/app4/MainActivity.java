package com.example.stud.app4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.example.stud.app4.Preferences.AppPrefs;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button butMenu;
    ListView lstGenerala;
    ArrayList<Stire> stiriArrayList ;
    StireArrayAdapter stireArrayAdapter;
    static ArrayList<Stire> buffer = new ArrayList<Stire>();

    public static ArrayList<Stire> getBuffer() {
        return buffer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stiriArrayList =  new ArrayList<Stire>();
        Log.e("MainActivyty", "On crate line 34");
        AppPrefs.getSharedPrefsLocal(this);
        Log.e("MainActivyty", "On crate line 36");
        lstGenerala = (ListView) findViewById(R.id.listViewGen);


        //stiriArrayList.add(new Stire("test", "test"));
        try {
            stireArrayAdapter = new StireArrayAdapter(this,
                    R.layout.activity_listview_item, stiriArrayList);
            lstGenerala.setAdapter(stireArrayAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.INTERNET);
//        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getApplicationContext(),
//                    Manifest.permission.INTERNET)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//                ActivityCompat.requestPermissions((Activity) getApplicationContext(),
//                        new String[]{Manifest.permission.INTERNET},
//                        113);
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions((Activity) getApplicationContext(),
//                        new String[]{Manifest.permission.INTERNET},
//                        113);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        } else {

//            for(Site i: Utils.surseArrayList.list){
////                if(i.isPref == true){
//                     new RssDataController() {
//                    @Override
//                    protected void onPostExecute(ArrayList<Stire> result) {
//                        MainActivity.getBuffer().addAll(result);
//                    }
//                    }.execute(i.url, " ", 0);
////                }
//            }
//
//            final Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    stireArrayAdapter.updateAdapter(buffer);
//                }
//            }, 300);
//        }
        Log.e("MainActivyty", "On crate line 65");
    }

    @Override
    protected void onStart() {
        super.onStart();

        // MainActivity.getBuffer().clear();
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

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (Site i : Utils.surseArrayList.list) {
                        if(i.isPref) {
                            new RssDataController() {
                                @Override
                                protected void onPostExecute(ArrayList<Stire> result) {
                                    MainActivity.getBuffer().addAll(result);
                                    stireArrayAdapter.updateAdapter(buffer);

                                }
                            }.execute(i.url, " ", 0);
                        }
                    }
                }

            });

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    stireArrayAdapter.updateAdapter(buffer);
                }
            }, 1000);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        buffer.clear();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 113: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (Site i : Utils.surseArrayList.list) {
                                if(i.isPref) {
                                    new RssDataController() {
                                        @Override
                                        protected void onPostExecute(ArrayList<Stire> result) {
                                            MainActivity.getBuffer().addAll(result);
                                            stireArrayAdapter.updateAdapter(buffer);

                                        }
                                    }.execute(i.url, " ", 0);
                                }
                            }
                        }

                    });

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stireArrayAdapter.updateAdapter(buffer);
                        }
                    }, 300);


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



    @Override
    protected void onStop() {
        super.onStop();
        AppPrefs.saveSharedPrefs(this);
    }
}
