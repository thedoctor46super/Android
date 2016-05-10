package com.maurizio.androidgit;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {
    public CustomTextView(Context ctx, AttributeSet attrs){
        super(ctx, attrs);
        this.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/JandaManateeSolid.ttf"));
    }
}
