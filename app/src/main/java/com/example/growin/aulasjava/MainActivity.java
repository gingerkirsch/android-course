package com.example.growin.aulasjava;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    final static String EXTRA_CLICKS = "clicks";
    private Button counter_btn, next_activity_btn;
    private TextView counter_text;
    private int clicks = 0;
    private Toast toast;

    @Override
    protected void onStart() {
        super.onStart();

        Log.v(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.v(TAG,"onStop");
        DataManager.saveIntPreference(DataManager.CLICKS, clicks, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.v(TAG,"onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.v(TAG,"onResume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clicks = DataManager.getIntPreference(DataManager.CLICKS, this);

        counter_btn = findViewById(R.id.button1);
        counter_text = findViewById(R.id.counter_text);
        counter_btn.setOnClickListener(this);

        next_activity_btn = findViewById(R.id.next_act);
        next_activity_btn.setOnClickListener(this);

        refreshBackground(findViewById(R.id.background), this);
        refreshCounter();

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clicks = 0;
                refreshCounter();
            }
        });

        findViewById(R.id.add_car).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AddCarActivity.class);
                startActivity(intent);
            }
        });

        counter_btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v){
                clicks = clicks + 5;
                refreshCounter();
                return true;
            }
        });

        Log.v(TAG,"onCreate");

        //applyFont();
    }


    /*private void applyFont(){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font.otf");
        counter_btn.setTypeface(typeface);
        next_activity_btn.setTypeface(typeface);

        ((Button)findViewById(R.id.add_car)).setTypeface(typeface);
        ((Button)findViewById(R.id.reset)).setTypeface(typeface);
        ((TextView)findViewById(R.id.counter_text)).setTypeface(typeface);
        FontManager fontManager = new FontManager();
        fontManager.applyFontToActivity(findViewById(R.id.background), "font.otf", this);
    }*/

    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.exit))
                .setTitle("Already leaving?");

        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                MainActivity.this.finish();
            }

        });
        builder.setNegativeButton(getResources().getString(R.string.no), null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v == counter_btn) {
            clicks++;
            refreshCounter();
            customToast.setTextSize(30);
            customToast.show("New click " + clicks, getApplicationContext());
        }
        if(v == next_activity_btn){
            goToMain2();
        }
    }

    private void refreshCounter(){
        String text = getResources().getQuantityString(R.plurals.clicks, clicks, clicks);
        counter_text.setText(text);
    }

    public static void refreshBackground(View view, Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String color = settings.getString("background", context.getResources().getString(R.string.grey));

        if (color.equals(context.getResources().getString(R.string.grey))) {
            view.setBackgroundResource(R.color.grey);
        }

        if (color.equals(context.getResources().getString(R.string.yellow))) {
            view.setBackgroundResource(R.color.yellow);
        }

        if (color.equals(context.getResources().getString(R.string.purple))) {
            view.setBackgroundResource(R.color.purple);
        }

    }

    public void goToMain2(){
        if (clicks < 5){
            showToast((5 - clicks) + " " + getResources().getString(R.string.needs_clicks));
            return;
        }
        Intent intent = new Intent ( this, Main2Activity.class);
        intent.putExtra(EXTRA_CLICKS, clicks);
        startActivity(intent);
        this.finish();
    }

    private void showToast(String message){
        if(toast != null && toast.getView().isShown()){
            toast.cancel();
        }

        toast = Toast.makeText(
                getApplicationContext(), message, Toast.LENGTH_SHORT
        );

        toast.show();
    }

}
