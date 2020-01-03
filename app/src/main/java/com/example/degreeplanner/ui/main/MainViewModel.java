//TODO: Remove this class and move to the new architecture pattern
package com.example.degreeplanner.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.degreeplanner.database.CourseEntity;
import com.example.degreeplanner.database.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<CourseEntity>> allCourses;
    private List<CourseEntity> mCourseList;
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

    LiveData<List<CourseEntity>> getAllCourses(){
        return allCourses;
    }

    public void insertCourse(CourseEntity course){
        repository.insertCourse(course);
    }

    void remove(CourseEntity course){
        repository.deleteCourse(course);
    }

    void updateCourse(CourseEntity courseEntity){
        repository.updateCourse(courseEntity);
    }

    List<CourseEntity> getmCourseList() {
        return mCourseList;
    }

    void setmCourseList(List<CourseEntity> mCourseList) {
        this.mCourseList = mCourseList;
    }

    ArrayList<CourseEntity> getUnscheduledCourses() {
        return unscheduledCourses;
    }

    public void setUnscheduledCourses(ArrayList<CourseEntity> unscheduledCourses) {
        this.unscheduledCourses = unscheduledCourses;
    }

    ArrayList<CourseEntity> getScheduledCourses() {
        return scheduledCourses;
    }

    public void setScheduledCourses(ArrayList<CourseEntity> scheduledCourses) {
        this.scheduledCourses = scheduledCourses;
    }

    ArrayList<ScheduledCourseArrayAdapter> getmAdapterList() {
        return mAdapterList;
    }

    public void setmAdapterList(ArrayList<ScheduledCourseArrayAdapter> mAdapterList) {
        this.mAdapterList = mAdapterList;
    }
}
