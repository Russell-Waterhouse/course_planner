package com.example.degreeplanner.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.degreeplanner.R;
import com.example.degreeplanner.ui.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity implements WelcomeFragment.OnWelcomeFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame, WelcomeFragment.newInstance(), null).commit();
    }

    public void viewScheduleButtonPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }



}
