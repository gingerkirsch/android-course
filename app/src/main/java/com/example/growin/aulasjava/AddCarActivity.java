package com.example.growin.aulasjava;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class AddCarActivity extends AppCompatActivity {
    EditText edit_brand, edit_model, edit_owner;
    Button enter;
    CheckBox noOwnerCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        enter = findViewById(R.id.enter);
        edit_brand = findViewById(R.id.edit_brand);
        edit_model = findViewById(R.id.edit_model);
        edit_owner = findViewById(R.id.edit_owner);
        noOwnerCheck = findViewById(R.id.noOwnerCheck);

        noOwnerCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit_owner.setEnabled(isChecked);
                findViewById(R.id.owner_layout).setAlpha(isChecked ? 0.5f : 1);
                //findViewById(R.id.owner_layout).setVisibility(isChecked ? View.GONE : View.VISIBLE); // alternative way
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                String text = res.getString(R.string.brand) + ":" + edit_brand.getText().toString() + " " +
                        res.getString(R.string.model) + ":" + edit_model.getText().toString();
                if (!noOwnerCheck.isChecked()) {
                    text += " " + res.getString(R.string.owner) + ":" + edit_owner.getText().toString();
                }
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
