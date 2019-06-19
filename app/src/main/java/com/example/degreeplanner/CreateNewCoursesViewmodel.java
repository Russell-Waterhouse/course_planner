package com.example.degreeplanner;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.degreeplanner.Database.CourseEntity;
import com.example.degreeplanner.Database.Repository;

public class CreateNewCoursesViewmodel extends AndroidViewModel {
    private Repository repository;



    public CreateNewCoursesViewmodel(Application app) {
        super(app);
        repository = new Repository(app);

    }

    public void insertCourse(CourseEntity course){
        repository.insertCourse(course);
    }
}
