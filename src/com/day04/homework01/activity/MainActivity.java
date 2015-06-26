package com.day04.homework01.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.day04.homework01.R;
import com.day04.homework01.fragment.BookmarkFragment;
import com.day04.homework01.fragment.NewsFragment;
import com.day04.homework01.fragment.WeatherFragment;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "MainActivity";
    // 两次返回键间隔，小于则退出
    private static final int TIME_INTERVAL = 2700;


    private FragmentManager manager;
    private RadioGroup rg_bar;
    private WeatherFragment weatherFragment;
    private NewsFragment newsFragment;
    private BookmarkFragment bookmarkFragment;
    private RadioButton rb_weather;
    // 第一次点击返回键记录时间，留作与第二次比较
    private long firstTime = 0L;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        manager = getSupportFragmentManager();
        rg_bar = (RadioGroup) this.findViewById(R.id.rg_bar);
        rg_bar.setOnCheckedChangeListener(this);
        rb_weather = (RadioButton) this.findViewById(R.id.rb_weather);

        // 初始化选中天气
        rb_weather.setChecked(true);
        changeFragment(R.id.rb_weather);
    }


    /**
     * RadioGroup 事件
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        changeFragment(checkedId);
    }


    /**
     * 切换Fragment
     *
     * @param checkedId 选中 RadioButton 的ID
     */
    private void changeFragment(int checkedId) {
        // TODO 切换 Fragment
        // 开事务
        FragmentTransaction transaction = manager.beginTransaction();

        switch (checkedId) {
            case R.id.rb_weather:
                if (weatherFragment == null) {
                    weatherFragment = new WeatherFragment();
                }
                transaction.replace(R.id.fl_container, weatherFragment, "weatherFragment");

                break;
            case R.id.rb_news:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                }
                transaction.replace(R.id.fl_container, newsFragment, "newsFragment");
                break;
            case R.id.rb_bookmark:
                if (bookmarkFragment == null) {
                    bookmarkFragment = new BookmarkFragment();
                }
                transaction.replace(R.id.fl_container, bookmarkFragment, "bookmarkFragment");
                break;
            default:
                break;
        }
        //提交事务
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        // 取本次点击返回键前时间
        long currentTime = System.currentTimeMillis();

        // 减去上一次点击返回键时间
        long intervalTime = currentTime - firstTime;


        // 两次返回键间隔大于设定时间范围则提示"再按一次退出应用~"，小于设定时间范围则退出应用
        if (intervalTime > TIME_INTERVAL) {
            firstTime = currentTime;
            Toast.makeText(MainActivity.this, "再按一次退出应用~", Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }


//        super.onBackPressed();
    }
}
