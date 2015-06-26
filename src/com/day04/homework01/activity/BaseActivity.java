package com.day04.homework01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created with IntelliJ IDEA
 * Project: day_04_homework01
 * User: longfeisun
 */
public class BaseActivity extends Activity {

    private static final String  TAG = "BaseActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, this.getClass().getSimpleName());
    }
}