package com.example.stud.app4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import static com.example.stud.app4.Preferences.AppPrefs.PREFS_SURSE_FILENAME;

public class NewWebsite extends AppCompatActivity {
    Button addSite;
    TextView titleTextView, urlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_website);

        urlTextView = (TextView) findViewById(R.id.editTextURL);

        addSite = (Button) findViewById(R.id.addSite);
        addSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences sharedPrefs = getSharedPreferences(PREFS_SURSE_FILENAME, MODE_PRIVATE);

                    Set<String> surseSet = sharedPrefs.getStringSet("surse", new HashSet<String>());
                    surseSet.add(urlTextView.getText().toString());

                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putStringSet("surse", surseSet);
                    editor.apply();

                    titleTextView.setText("");
                    urlTextView.setText("");

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


}
