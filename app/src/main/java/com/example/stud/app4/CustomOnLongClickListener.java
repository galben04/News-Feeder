package com.example.stud.app4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * Created by Masina Personala on 12/1/2016.
 */

public class CustomOnLongClickListener implements View.OnLongClickListener {
    private final int position;
    private final SurseArrayAdapter adapter;

    public CustomOnLongClickListener(int poz, SurseArrayAdapter adapter) {
        this.position = poz;
        this.adapter = adapter;
    }

    @Override

        public boolean onLongClick(View view) {
            final Site actualSite = Utils.surseArrayList.list.get(position);

            final String[] items = {"Sterge", "Editeaza"};
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            //builder.setTitle("Make your selection");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch (item){
                        case 0:
                            Utils.surseArrayList.list.remove(position);
                            Utils.hasChangedSurse = true;
                            break;
                        case 1:
                            Utils.surseArrayList.list.remove(position);
                            Intent intent = new Intent(adapter.getContext(), NewWebsite.class);
                            intent.putExtra("titlu", actualSite.titlu);
                            intent.putExtra("URL", actualSite.url);

                            adapter.getContext().startActivity(intent);
                            break;
                        default:
                            dialog.dismiss();
                    }
                }
            });

        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                adapter.updateAdapter(Utils.surseArrayList.list);
            }
        });
            AlertDialog alert = builder.create();
            alert.show();

            Log.v("long clicked","pos: " + position);

            return true;
    }
}
