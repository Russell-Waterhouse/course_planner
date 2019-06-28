package com.example.degreeplanner;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.degreeplanner.Adapter.ScheduledCourseArrayAdapter;
import com.example.degreeplanner.Database.CourseEntity;
import com.example.degreeplanner.Database.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<CourseEntity>> allCourses;
    List<CourseEntity> mCourseList;
    private ArrayList<CourseEntity> unscheduledCourses;
    private ArrayList<CourseEntity> scheduledCourses;
    private ArrayList<ScheduledCourseArrayAdapter> mAdapterList;

    public MainViewModel(Application app) {
        super(app);
        repository = new Repository(app);
        allCourses = repository.getAllCourse();
        unscheduledCourses = new ArrayList<>();
        scheduledCourses = new ArrayList<>();
        mAdapterList = new ArrayList<>();
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

    public void updateCourse(CourseEntity courseEntity){
        repository.updateCourse(courseEntity);
    }

    public List<CourseEntity> getmCourseList() {
        return mCourseList;
    }

    public void setmCourseList(List<CourseEntity> mCourseList) {
        this.mCourseList = mCourseList;
    }

    public ArrayList<CourseEntity> getUnscheduledCourses() {
        return unscheduledCourses;
    }

    public void setUnscheduledCourses(ArrayList<CourseEntity> unscheduledCourses) {
        this.unscheduledCourses = unscheduledCourses;
    }

    public ArrayList<CourseEntity> getScheduledCourses() {
        return scheduledCourses;
    }

    public void setScheduledCourses(ArrayList<CourseEntity> scheduledCourses) {
        this.scheduledCourses = scheduledCourses;
    }

    public ArrayList<ScheduledCourseArrayAdapter> getmAdapterList() {
        return mAdapterList;
    }

    public void setmAdapterList(ArrayList<ScheduledCourseArrayAdapter> mAdapterList) {
        this.mAdapterList = mAdapterList;
    }
}
