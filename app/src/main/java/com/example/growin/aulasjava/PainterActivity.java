package com.example.growin.aulasjava;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PainterActivity extends AppCompatActivity {

    List<RandomPaintFragment> fragmentsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painter);
    }

    public void addFragment(View view){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragmentsList.add(new RandomPaintFragment());
        ft.add(R.id.layout, fragmentsList.get(fragmentsList.size() - 1));
        ft.commit();
    }

    public void deleteFragment(View view){
        if (fragmentsList.size() > 0) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(fragmentsList.get(fragmentsList.size() - 1));
            fragmentsList.remove(fragmentsList.get(fragmentsList.size() - 1));
            ft.commit();
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(PainterActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        this.finish();
    }
}
