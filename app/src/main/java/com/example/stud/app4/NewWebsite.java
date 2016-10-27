package com.example.stud.app4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NewWebsite extends AppCompatActivity {
    Button addSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_website);

        addSite = (Button) findViewById(R.id.addSite);
        addSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////adauga url in lista
            }
        });
    }


}
