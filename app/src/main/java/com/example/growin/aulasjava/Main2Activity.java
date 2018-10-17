package com.example.growin.aulasjava;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private int clicks = 0;
    private boolean isRed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        MainActivity.refreshBackground(findViewById(R.id.background), this);

        TextView counter = findViewById(R.id.counter_text);

        Intent extData = getIntent();
        clicks = extData.getIntExtra(MainActivity.EXTRA_CLICKS, 0);

        findViewById(R.id.show_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNotification();
            }
        });

        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDialog();
            }
        });

        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, SettingsActivity.class);
                startActivity(intent);
                Main2Activity.this.finish();
            }
        });
        findViewById(R.id.keyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, KeyboardActivity.class);
                startActivity(intent);
            }
        });


        counter.setText(getResources().getQuantityString(R.plurals.clicks, clicks, clicks));
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void launchNotification(){
        Notification.Builder mBuilder = new Notification.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getResources().getString(R.string.title))
                        .setContentText(getResources().getQuantityString(R.plurals.clicks, clicks, clicks));
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }

    public void launchDialog(){
        String text = isRed ? getResources().getString(R.string.changeColorBlue) : getResources().getString(R.string.changeColorRed);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setTitle(getResources().getString(R.string.dialog));
        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                isRed = !isRed;
                refreshColors();
            }

        });
        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){}

        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void refreshColors(){
        findViewById(R.id.show_notification).setBackgroundResource(isRed ? R.color.red : R.color.blue);
        findViewById(R.id.dialog).setBackgroundResource(isRed ? R.color.red : R.color.blue);

    }
}