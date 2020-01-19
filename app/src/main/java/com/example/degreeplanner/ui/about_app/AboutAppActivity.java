package com.example.degreeplanner.ui.about_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.degreeplanner.R;
import com.example.degreeplanner.ui.about_app.fragments.AboutDevelopmentFragment;
import com.example.degreeplanner.ui.about_app.fragments.OpenSourceContributionsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;


public class AboutAppActivity extends AppCompatActivity
        implements AboutDevelopmentFragment.OnDevelopmentFragmentInteractionListener,
        OpenSourceContributionsFragment.OnOpenSourceFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.about_app);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

    @Override
    public void seeSourceButtonPushed() {
        Intent seeCodeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.link_to_code)));
        startActivity(seeCodeIntent);
    }

    @Override
    public void androidSdkButtonPressed() {
        Intent androidInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.android_sdk_url)));
        startActivity(androidInBrowser);
    }

    @Override
    public void roomLibraryButtonPressed() {
        Intent roomInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.room_url)));
        startActivity(roomInBrowser);
    }

    @Override
    public void liveDataLibraryButtonPressed() {
        Intent liveDataInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.lifecycle_library_url)));
        startActivity(liveDataInBrowser);
    }
}