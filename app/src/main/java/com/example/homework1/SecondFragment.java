package com.example.homework1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class SecondFragment extends Fragment {
    private static final String CURRENT = "CURRENT";
    private int num;

    public SecondFragment() {}
    SecondFragment(int n) {
        num = n;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            num = savedInstanceState.getInt(CURRENT);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        TextView counter = view.findViewById(R.id.num);
        counter.setText(String.valueOf(num));
        int color;
        if(num%2 == 0)
            color = Color.RED;
        else
            color = Color.BLUE;
        counter.setTextColor(color);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT, num);
    }
}
