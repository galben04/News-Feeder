package com.example.stud.app4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Surse extends AppCompatActivity {
    private ListView surseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surse);

        surseListView = (ListView) findViewById(R.id.surse_listview);

        String[] arrayString = {"www.Google.ro", "www.yahoo.com", "www.lideriidemaine.ro", "www.peluzasud.org", "www.protv.ro"};
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                                                R.layout.acativity_listview, arrayString);

        surseListView.setAdapter(adapter);
    }
}
