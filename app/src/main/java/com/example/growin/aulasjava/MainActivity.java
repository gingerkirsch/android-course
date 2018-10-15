package com.example.growin.aulasjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";
    final static String EXTRA_CLICKS = "clicks";
    private Button counter_btn, next_activity_btn;
    private TextView counter_text;
    private int clicksCounter = 0;

    @Override
    protected void onStart() {
        super.onStart();

        Log.v(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.v(TAG,"onStop");
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

        counter_btn = findViewById(R.id.button1);
        counter_text = findViewById(R.id.counter_text);
        counter_btn.setOnClickListener(this);

        next_activity_btn = findViewById(R.id.next_act);
        next_activity_btn.setOnClickListener(this);

        counter_btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v){
                clicksCounter = clicksCounter + 5;
                refreshButton();
                return true;
            }
        });

        Log.v(TAG,"onCreate");
    }

    @Override
    public void onClick(View v) {
        if (v == counter_btn) {
            clicksCounter++;
            refreshButton();
        }
        if(v == next_activity_btn){
            goToMain2();
        }
    }

    private void refreshButton(){
        String text = getResources().getQuantityString(R.plurals.clicks, clicksCounter, clicksCounter);
        counter_text.setText(text);
    }

    public void goToMain2(){
        if (clicksCounter < 5){
            showToast((5 - clicksCounter) + " " + getResources().getString(R.string.needs_clicks));
            return;
        }
        Intent intent = new Intent ( this, Main2Activity.class);
        intent.putExtra(EXTRA_CLICKS, clicksCounter);
        startActivity(intent);
        this.finish();
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
