package com.example.stud.app4.Preferences;

/**
 * Created by CaNou on 10/29/2016.
 */

public class AppPrefs {
    public static final String PREFS_SURSE_FILENAME = "AppPrefs";


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
