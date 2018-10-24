package com.example.growin.aulasjava;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Random;


public class RandomPaintFragment extends Fragment {

    float initialX;
    float minimumDis = 150;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View parent = inflater.inflate(R.layout.fragment_random_paint, container, false);
        generateColors(parent);

        setLayoutParams(parent, R.id.layout1);
        setLayoutParams(parent, R.id.layout2);
        setLayoutParams(parent, R.id.layout3);

        parent.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        float finalX = event.getX();
                        if (Math.abs(finalX - initialX) < minimumDis) break;
                        if (initialX > finalX){
                            generateColors(parent);
                        } else {
                            removeColors(parent);
                        }
                        break;
                }
                return true;
            }
        });

        return parent;
    }

    private void setLayoutParams(View parent, int id){
        Random random = new Random();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                random.nextInt(5)
        );

        parent.findViewById(id).setLayoutParams(params);
    }

    private void generateColors(View parent){
        Random rnd = new Random();
        final int color1 = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        final int color2 = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        final int color3 = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        parent.findViewById(R.id.layout1).setBackgroundColor(color1);
        parent.findViewById(R.id.layout2).setBackgroundColor(color2);
        parent.findViewById(R.id.layout3).setBackgroundColor(color3);
    }

    private void removeColors(View parent){
        Random rnd = new Random();
        final int transparent = Color.argb(0,0,0,0);
        parent.findViewById(R.id.layout1).setBackgroundColor(transparent);
        parent.findViewById(R.id.layout2).setBackgroundColor(transparent);
        parent.findViewById(R.id.layout3).setBackgroundColor(transparent);
    }

}
