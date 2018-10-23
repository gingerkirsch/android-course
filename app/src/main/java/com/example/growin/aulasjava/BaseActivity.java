package com.example.growin.aulasjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class BaseActivity extends AppCompatActivity {

    private FontManager fontManager;
    public CustomToast customToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        customToast = new CustomToast();
        fontManager = new FontManager();
        fontManager.setTypeface("font.otf", this);
        customToast.setTypeface("font.otf", this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fontManager.applyFontToActivity(findViewById(android.R.id.content), "font.otf", this);
    }

}
