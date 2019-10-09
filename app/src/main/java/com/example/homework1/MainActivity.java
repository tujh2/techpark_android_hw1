package com.example.homework1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private static final int DEF_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(savedInstanceState == null) {
            transaction.add(R.id.mainFrag, MainFragment.newInstance(DEF_SIZE));
            transaction.commit();
        }
    }
}