package com.example.stud.app4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


enum RSSXMLTag {
    TITLE, DATE, LINK, CONTENT, GUID, IGNORETAG, DESCRIPTION;
}

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
        if(URL == null) {
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
            new RssDataController().execute(URL);
        }

    }

    public void updateAdapter(ArrayList<Stire> result) {
        adapter.clear();
        adapter.addAll(result);

        adapter.notifyDataSetChanged();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 113: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    new RssDataController().execute(URL);

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

    private class RssDataController extends AsyncTask< //comment >
            String,
            Integer,
            ArrayList<Stire>> {

        private RSSXMLTag currentTag;


        @Override
        protected ArrayList<Stire> doInBackground(String... params) {

            // TODO Auto-generated method stub
            String urlStr = params[0];
            InputStream is = null;

            ArrayList<Stire> postStiriList = new ArrayList<Stire>();


            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10 * 1000);
                connection.setConnectTimeout(10 * 1000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                int response = connection.getResponseCode();
                Log.d("debug", "The response is: " + response);
                is = connection.getInputStream();

                // parse xml after getting the data
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);

                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(is, null);

                int eventType = xpp.getEventType();
                Stire stire = null;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {

                    } else if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equals("item")) {
                            stire = new Stire();
                            currentTag = RSSXMLTag.IGNORETAG;
                        } else if (xpp.getName().equals("title")) {
                            currentTag = RSSXMLTag.TITLE;
                        } else if (xpp.getName().equals("link")) {
                            currentTag = RSSXMLTag.LINK;
                        } else if (xpp.getName().equals("description")) {
                            currentTag = RSSXMLTag.DESCRIPTION;
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (xpp.getName().equals("item")) {
                            // format the data here, otherwise format data in
                            // Adapter

                            postStiriList.add(stire);
                        } else {
                            currentTag = RSSXMLTag.IGNORETAG;
                        }
                    } else if (eventType == XmlPullParser.TEXT) {
                        String content = xpp.getText();
                        content = content.trim();
                        Log.d("debug", content);
                        if (stire != null) {
                            switch (currentTag) {
                                case TITLE:
                                    if (content.length() != 0) {
                                        if (stire.titlu != null) {
                                            stire.titlu += content;
                                        } else {
                                            stire.titlu = content;
                                        }
                                    }
                                    break;

                                case LINK:
                                    if (content.length() != 0) {
                                        if (stire.urlSursa != null) {
                                            stire.urlSursa += content;
                                        } else {
                                            stire.urlSursa = content;
                                        }
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (content.length() != 0) {
                                        if (stire.text != null) {
                                            stire.text += content;
                                        } else {
                                            stire.text = content;
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    eventType = xpp.next();
                }
                Log.v("tst", String.valueOf((postStiriList.size())));
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return postStiriList;
        }

        @Override
        protected void onPostExecute(ArrayList<Stire> result) {
            // TODO Auto-generated method stub

            updateAdapter(result);

        }
    }
}

