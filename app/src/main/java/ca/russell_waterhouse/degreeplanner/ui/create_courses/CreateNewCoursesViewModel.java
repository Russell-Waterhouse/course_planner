package ca.russell_waterhouse.degreeplanner.ui.create_courses;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import ca.russell_waterhouse.degreeplanner.database.CourseEntity;
import ca.russell_waterhouse.degreeplanner.database.Repository;

public class CreateNewCoursesViewModel extends AndroidViewModel {
    private Repository repository;

    public CreateNewCoursesViewModel(Application app) {
        super(app);
        repository = new Repository(app);

    }

    void insertCourse(CourseEntity course){
        repository.insertCourse(course);
    }
}
