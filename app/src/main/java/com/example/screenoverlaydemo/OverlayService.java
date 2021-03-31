package com.example.screenoverlaydemo;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class OverlayService extends Service {

    public static String TAG = "Sebverlay";
    public OverlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public void onCreate() {
        super.onCreate();

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.LEFT;

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View mTopView = inflater.inflate(R.layout.fullscreen_overlay, null);

        mTopView.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View v, MotionEvent event) {
                Log.d("seb-generic", event.toString());
                return false;
            }
        });

        mTopView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("seb-touch", event.toString());
                return false;
            }
        });

        mTopView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("seb-click", v.toString());
            }
        });

        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        wm.addView(mTopView, params);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}