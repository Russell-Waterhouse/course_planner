package com.example.degreeplanner.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.degreeplanner.R;
import com.example.degreeplanner.ui.about_app.AboutAppActivity;
import com.example.degreeplanner.ui.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity
        implements WelcomeFragment.OnWelcomeFragmentInteractionListener{

    private WelcomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel.class);
        FragmentManager manager = getSupportFragmentManager();
        String welcomeFragmentTAG = "WELCOME_FRAGMENT";
        manager.beginTransaction().add(R.id.frame, WelcomeFragment.newInstance(), welcomeFragmentTAG).commit();
    }

    @Override
    public void viewScheduleButtonPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void createNewScheduleButtonPressed(){
        viewModel.deleteAllData();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public void aboutAppButtonPressed(){
        startActivity(new Intent(getApplicationContext(), AboutAppActivity.class));
    }
}
