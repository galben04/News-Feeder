package com.example.stud.app4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Teste extends AppCompatActivity {
        ImageButton buttonGoTo, buttonDetails;
    LinearLayout layout;
    TextView textViewDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_stire_layout);

        buttonGoTo = (ImageButton) findViewById(R.id.btnGoToSursa);
        buttonDetails = (ImageButton) findViewById(R.id.btnDetaliiStire);
        layout = (LinearLayout) findViewById(R.id.detailsLinearLayout);
        textViewDetails = (TextView) findViewById(R.id.detailsTextView);
        textViewDetails.setText("testtesttesttesttesttesttesttesttest testtesttesttesttesttest testtesttest testtest testtest testtesttesttesttest testtesttest vv v");
        buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(layout.getVisibility() == View.GONE) {
                    buttonDetails.setBackgroundResource(R.mipmap.up_arrow);
                    layout.setVisibility(View.VISIBLE);
                }else{
                    buttonDetails.setBackgroundResource(R.mipmap.down_arrow);
                    layout.setVisibility(View.GONE);
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
        }else if(opt == R.id.opt_teste) {
            Intent intent = new Intent(getApplicationContext(), Teste.class);
            startActivity(intent);
        }
        return true;
    }

}
