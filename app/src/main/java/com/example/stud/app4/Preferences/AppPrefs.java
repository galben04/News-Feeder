package com.example.stud.app4.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.stud.app4.Site;
import com.example.stud.app4.SurseArrayList;
import com.example.stud.app4.Utils;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;
import static com.example.stud.app4.Utils.surseArrayList;


/**
 * Created by CaNou on 10/29/2016.
 */

public class AppPrefs {
    public static final String PREFS_FILENAME = "AppPrefs";
    public static final String PREFS_SURSE = "surse";

    public static int MY_PERMISSIONS_REQUEST_READ_CONTACTS;

    public static void getSharedPrefsLocal(Context context){
        Log.e("AppPrefs", " 26");
        if(Utils.surseArrayList == null)
            Utils.surseArrayList = new SurseArrayList();

        SharedPreferences mPrefs = context.getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json1 = mPrefs.getString(AppPrefs.PREFS_SURSE, "");

        Utils.surseArrayList = gson.fromJson(json1, SurseArrayList.class);

        if(Utils.surseArrayList == null) {
            Utils.surseArrayList = new SurseArrayList();
            Utils.surseArrayList.list.add(new Site("http://www.tested.com/podcast-xml/",true,"Tested"));
        }

        Utils.hasChangedSurse = false;
    }

    public static void saveSharedPrefs(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        Gson gson = new Gson();
        String json1 = gson.toJson(surseArrayList);

        prefsEditor.clear();
        prefsEditor.putString(AppPrefs.PREFS_SURSE, json1);
        prefsEditor.apply();
    }

//    public static SharedPreferences getSharedPreferences (Context ctxt) {
//        return ctxt.getSharedPreferences(PREFS_SURSE_FILENAME, 0);
//    }

//    public static void addStringInSurseSet(Context conxt,String elem) {
//        SharedPreferences sharedPrefs = getSharedPreferences(AppPrefs.PREFS_SURSE_FILENAME, conxt.MODE_PRIVATE);
//        Set<String> surseSet = sharedPrefs.getStringSet("surse", null);
//        surseSet.add(elem);
//
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        editor.putStringSet(PREFS_SURSE_FILENAME, surseSet);
//        editor.apply();
//    }
    //
//    public static getPrefs(Context ctxt) {
//        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//        boolean silent = settings.getBoolean("silentMode", false);
//        setSilent(silent);
//    }
//    @Override
//        protected void onStop(){
//            super.onStop();
//
//            // We need an Editor object to make preference changes.
//            // All objects are from android.context.Context
//            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//            SharedPreferences.Editor editor = settings.edit();
//            editor.putBoolean("silentMode", mSilentMode);
//
//            // Commit the edits!
//            editor.commit();
//        }
//    }

}
