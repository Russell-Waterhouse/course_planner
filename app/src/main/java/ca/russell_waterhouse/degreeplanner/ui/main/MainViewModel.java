package ca.russell_waterhouse.degreeplanner.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import ca.russell_waterhouse.degreeplanner.database.CourseEntity;
import ca.russell_waterhouse.degreeplanner.database.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<CourseEntity>> allCourses;
    private List<CourseEntity> courseList;
    private ArrayList<CourseEntity> unscheduledCourses;
    private ArrayList<CourseEntity> scheduledCourses;
    private ArrayList<ScheduledCourseArrayAdapter> adapterList;

    public MainViewModel(Application app) {
        super(app);
        repository = new Repository(app);
        allCourses = repository.getAllCourse();
        unscheduledCourses = new ArrayList<>();
        scheduledCourses = new ArrayList<>();
        adapterList = new ArrayList<>();
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

    List<CourseEntity> getCourseList() {
        return courseList;
    }

    void setCourseList(List<CourseEntity> courseList) {
        this.courseList = courseList;
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

    ArrayList<ScheduledCourseArrayAdapter> getAdapterList() {
        return adapterList;
    }

    public void setAdapterList(ArrayList<ScheduledCourseArrayAdapter> adapterList) {
        this.adapterList = adapterList;
    }
}
