package com.day04.homework01.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import com.day04.homework01.R;
import com.day04.homework01.util.XmlUtil;

/**
 * Created with IntelliJ IDEA
 * Project: day_04_homework01
 * User: longfeisun
 */
public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //假装检查更新
        checkUpdate();
    }

    private void checkUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);

                    SharedPreferences pref = getSharedPreferences("homework.xml", Context.MODE_PRIVATE);
                    // 判断有没有加载过省市区信息
                    if(!pref.getBoolean("hasInitWeatherCity", false)){
                        // TODO 从 xml 中加载城市信息到数据库中
                        XmlUtil.initWeatherCityXMl(SplashActivity.this);
                    }

                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}