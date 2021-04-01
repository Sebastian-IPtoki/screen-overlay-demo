package com.example.screenoverlaydemo;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CatchView extends ConstraintLayout {

    public static String TAG = "Seb CatchView";

    public CatchView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "Event Catchview");
        performClick();
        return false;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
