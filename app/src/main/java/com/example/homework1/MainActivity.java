package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    public static MainActivity INSTANCE;
    private Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INSTANCE = this;
        navigator = new Navigator();
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFrag, MainFragment.newInstance());
            transaction.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        navigator = null;
        INSTANCE = null;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    class Navigator implements FragmentNavigator {
        private final FragmentManager fragmentManager = getSupportFragmentManager();
        @Override
        public void navigateToSecondFragment(int num, int col) {
            Fragment mainFrag = fragmentManager.findFragmentById(R.id.mainFrag);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if(mainFrag != null)
                transaction.remove(mainFrag);
            transaction.replace(R.id.secFrag, SecondFragment.newInstance(num, col));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}