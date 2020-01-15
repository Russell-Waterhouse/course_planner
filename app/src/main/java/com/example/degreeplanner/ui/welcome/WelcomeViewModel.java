package com.example.degreeplanner.ui.welcome;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.degreeplanner.database.Repository;

public class WelcomeViewModel extends AndroidViewModel {

    private Repository repository;

    public WelcomeViewModel(Application app){
        super(app);
        repository = new Repository(app);
    }

    void deleteAllData(){
        repository.deleteAllData();
    }
}
