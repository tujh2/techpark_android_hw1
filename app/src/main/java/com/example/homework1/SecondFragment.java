package com.example.homework1;

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
    private static final String COLOR = "COLOR";
    private int num, color;
    private TextView counter = null;

    static SecondFragment newInstance(int n, int col) {
        SecondFragment frag = new SecondFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT, n);
        bundle.putInt(COLOR, col);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        Bundle arguments = getArguments();
        if(savedInstanceState != null) {
            num = savedInstanceState.getInt(CURRENT);
            color = savedInstanceState.getInt(COLOR);
        }
        if(arguments != null) {
            num = arguments.getInt(CURRENT);
            color = arguments.getInt(COLOR);
        }
        counter = view.findViewById(R.id.num);
        counter.setText(String.valueOf(num));
        counter.setTextColor(color);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        counter = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT, num);
        outState.putInt(COLOR, color);
    }
}
