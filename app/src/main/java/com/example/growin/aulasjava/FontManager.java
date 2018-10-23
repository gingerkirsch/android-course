package com.example.growin.aulasjava;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FontManager {
    Typeface typeface = null;

    public void applyFontToActivity(View root, String fontNameFromAssets, Context context){

        setTypeface(fontNameFromAssets, context);

        for (int index = 0; index < ((ViewGroup)root).getChildCount(); ++index){
            View nextChild = ((ViewGroup)root).getChildAt(index);
            if (nextChild instanceof TextView){
                ((TextView)nextChild).setTypeface(typeface);
            }

            if (nextChild instanceof Button){
                ((Button)nextChild).setTypeface(typeface);
                //((Button)nextChild).setTransformationMethod(null); - we can switch off default allCapsLock
            }

            if (nextChild instanceof EditText){
                ((EditText)nextChild).setTypeface(typeface);
            }

            if (nextChild instanceof RelativeLayout || nextChild instanceof LinearLayout || nextChild instanceof FrameLayout){
                applyFontToActivity(nextChild, fontNameFromAssets, context);
            }
        }
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(String fontNameFromAssets, Context context) {
        this.typeface = Typeface.createFromAsset(context.getAssets(), fontNameFromAssets);
    }
}
