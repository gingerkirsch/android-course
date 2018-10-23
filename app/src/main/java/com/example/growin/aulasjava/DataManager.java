package com.example.growin.aulasjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DataManager {

    public final static String CLICKS = "CLICKS";
    public final static String IS_RED = "IS_RED";

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

    public static void saveBooleanPreference(String key, boolean value, Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBooleanPreference(String string, Boolean default_value, Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getBoolean(string, default_value);
    }

    public static boolean getBooleanPreference(String string, Context context){
        return getBooleanPreference(string, false, context);
    }
}
