package com.example.degreeplanner.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.degreeplanner.R;
import com.example.degreeplanner.ui.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity
        implements WelcomeFragment.OnWelcomeFragmentInteractionListener,
        NewScheduleFragment.OnFragmentInteractionListener {
    private final String welcomeFragmentTAG = "WELCOME_FRAGMENT";
    private final String newScheduleFragmentTAG = "NEW_SCHEDULE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame, WelcomeFragment.newInstance(), welcomeFragmentTAG).commit();
    }

    @Override
    public void viewScheduleButtonPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void createNewScheduleButtonPressed(){
        FragmentManager manager = getSupportFragmentManager();
        Fragment currentFragment = manager.findFragmentByTag(welcomeFragmentTAG);
        if (currentFragment != null) {
            manager.beginTransaction().hide(currentFragment).add(R.id.frame, NewScheduleFragment.newInstance(), newScheduleFragmentTAG).commit();
        }
    }

    @Override
    public void aboutAppButtonPressed(){
//        TODO: Finish implementation
    }

    @Override
    public void createScheduleFromScratch() {
//        TODO: Finish method
    }

    @Override
    public void createScheduleFromTemplate() {
//      TODO: finish method
    }
}
