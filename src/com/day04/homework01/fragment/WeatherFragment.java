package com.day04.homework01.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.day04.homework01.R;

/**
 * Created with IntelliJ IDEA
 * Project: day_04_homework01
 * User: longfeisun
 */
public class WeatherFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }
}