package com.example.degreeplanner;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.degreeplanner.Database.CourseEntity;
import com.example.degreeplanner.Database.Repository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<CourseEntity>> allCourses;

    public MainViewModel(Application app) {
        super(app);
        repository = new Repository(app);
        allCourses = repository.getAllCourse();
    }

    public LiveData<List<CourseEntity>> getAllCourses(){
        return allCourses;
    }

    public void insertCourse(CourseEntity course){
        repository.insertCourse(course);
    }

    public void remove(CourseEntity course){
        repository.deleteCourse(course);
    }

}
