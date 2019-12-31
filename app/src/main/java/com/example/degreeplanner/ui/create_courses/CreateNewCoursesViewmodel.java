package com.example.degreeplanner.ui.create_courses;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.degreeplanner.database.CourseEntity;
import com.example.degreeplanner.database.Repository;

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
