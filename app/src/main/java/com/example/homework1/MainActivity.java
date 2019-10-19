package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private static Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigator = new Navigator();
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.Frag, MainFragment.newInstance());
            transaction.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        navigator = null;
    }

    public static Navigator getNavigator() {
        return navigator;
    }

    class Navigator implements FragmentNavigator {
        private final FragmentManager fragmentManager = getSupportFragmentManager();
        private final Fragment fragment = fragmentManager.findFragmentById(R.id.Frag);
        @Override
        public void navigateToSecondFragment(int num, int col) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if(fragment != null)
                transaction.remove(fragment);
            transaction.replace(R.id.Frag, SecondFragment.newInstance(num, col));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}