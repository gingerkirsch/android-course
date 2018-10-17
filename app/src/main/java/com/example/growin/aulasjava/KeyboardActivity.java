package com.example.growin.aulasjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KeyboardActivity extends AppCompatActivity {

    StringBuilder txt;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        txt = new StringBuilder();
        textView = findViewById(R.id.textview);

        findViewById(R.id.erase).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v){
                if (txt.length() > 0){
                    do {
                        String aux = txt.substring(0, txt.length() - 1);
                        txt = new StringBuilder(aux);
                    } while (txt.length() > 0 && txt.charAt(txt.length() - 1) != ' ');
                        textView.setText(txt);
                }
                return true;
            }
        });
    }

    public void keyboardPress(View view){
        if (view == findViewById(R.id.erase)){
            if (txt.length() > 0){
                String aux = txt.substring(0, txt.length() - 1);
                txt = new StringBuilder(aux);
            } else {
                return;
            }
        } else if (view == findViewById(R.id.space)){
            txt.append(" ");
        } else {
            Button btn = (Button) view;
            txt.append(btn.getText());
        }
        textView.setText(txt);
    }
}
