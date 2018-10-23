package com.example.growin.aulasjava;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {
    Toast toast;
    Typeface typeface = null;
    private int textSize = 25;

    public void show(String message, Context context){
        show(message, null, context);
    }

    public void show(String message, String fontNameFromAssets, Context context){
        if (toast != null && toast.getView().isShown()){
            toast.cancel();
        }
        if (fontNameFromAssets != null){
            setTypeface(fontNameFromAssets, context);
        }
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View toastView = toast.getView();
        TextView toastMessage = toastView.findViewById(android.R.id.message);
        if (toastMessage != null){
            toastMessage.setGravity(Gravity.CENTER);
            toastMessage.setTextSize(textSize);
            toastMessage.setTextColor(Color.WHITE);
            if (typeface != null){
                toastMessage.setTypeface(typeface);
            }
        }
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0,0);
        toastView.setBackgroundColor(Color.TRANSPARENT);
        toast.show();
    }

    public void setTypeface(String fontNameFromAssets, Context context) {
        this.typeface = Typeface.createFromAsset(context.getAssets(), fontNameFromAssets);
    }

    public void setTextSize(int textSize){
        this.textSize = textSize;
    }

}
