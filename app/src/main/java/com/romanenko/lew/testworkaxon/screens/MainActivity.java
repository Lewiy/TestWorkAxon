package com.romanenko.lew.testworkaxon.screens;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.romanenko.lew.testworkaxon.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();

        ListUsersFragment listUsersFragment = ListUsersFragment.newInstance();
        fm.beginTransaction()
                .replace(R.id.content_frame_main_activity, listUsersFragment, listUsersFragment.getClass().toString())
                .addToBackStack(listUsersFragment.getClass().toString())
                .commit();
    }
}
