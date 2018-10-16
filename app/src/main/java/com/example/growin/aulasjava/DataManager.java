package com.example.growin.aulasjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DataManager {

    public final static String CLICKS = "CLICKS";

    public static void saveIntPreference(String string, int value, Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(string, value);
        editor.apply();
    }

    public static int getIntPreference(String string, Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getInt(string, 0);
    }
}
