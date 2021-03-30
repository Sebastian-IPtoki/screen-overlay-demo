package com.example.screenoverlaydemo;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.AndroidRuntimeException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;

public class OverlayAccessibilityService extends AccessibilityService {

    private static final String TAG = "SebAccess";

    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onServiceConnected() {
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        info.packageNames = null;

        info.notificationTimeout = 0;
        this.setServiceInfo(info);

        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        Log.d(TAG, "Accessibility Service connected.");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        if (source == null) {
            return;
        }
        Log.d(TAG, "Infos onAccessibilityEvent --- ");
        Log.d(TAG, "Event : " + accessibilityEvent);
        Log.d(TAG, "Description : " + source.getContentDescription());
        Log.d(TAG, "Actions : " + source.getActionList());
        Log.d(TAG, "Package name : " + source.getPackageName());
        Log.d(TAG, "Window : " + source.getWindow());
        Log.d(TAG, "Collection Infos : " + source.getCollectionInfo());
        Log.d(TAG, "ViewId Name : " + source.getViewIdResourceName());
        Log.d(TAG, "Extra Data : " + source.getAvailableExtraData());
        Log.d(TAG, "Class Name : " + source.getClassName());
        Log.d(TAG, "Extra : " + source.getExtras());
        Log.d(TAG, "Live region : " + source.getLiveRegion());
        Log.d(TAG, "Input type : " + source.getInputType());
        Log.d(TAG, "Text selection start : " + source.getTextSelectionStart());
        Log.d(TAG, "Text selection end : " + source.getTextSelectionEnd());
        Log.d(TAG, "Movement granularity : " + source.getMovementGranularities());
        Log.d(TAG, "Traversal before : " + source.getTraversalBefore());
        Log.d(TAG, "Traversal after : " + source.getTraversalAfter());
        Log.d(TAG, "Collection info : " + source.getCollectionInfo());
        Log.d(TAG, "Collection Item info : " + source.getCollectionItemInfo());
//        Log.d(TAG, "Screen click " + source.get + " - Screen -> W:" + screenWidth + " H:" + screenHeight);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Log.d(TAG, "Extra rendering info : " + source.getExtraRenderingInfo());
            Log.d(TAG, "State description : " + source.getStateDescription());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Log.d(TAG, "Touch delegate info " + source.getTouchDelegateInfo());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Log.d(TAG, "Pane title " + source.getPaneTitle());
        }
    }

    @Override
    public boolean onGesture(@NonNull AccessibilityGestureEvent gestureEvent) {
        Log.d(TAG, "onGestureEvent: event ");
        return super.onGesture(gestureEvent);
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Log.d(TAG, "onKeyEvent: event ");
        return super.onKeyEvent(event);
    }

    @Override
    public void onInterrupt() {

    }
}