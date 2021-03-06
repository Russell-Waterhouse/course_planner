package ca.russell_waterhouse.degreeplanner.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private static Database db;
    private CourseDAO dao;

    public Repository(Application app){
        db = Database.getDataBase(app);
        dao = db.courseDAO();
    }

    public void insertCourse(CourseEntity course){
        new insertCourseAsyncTask(dao).execute(course);
    }

    public void deleteCourse(CourseEntity course){
        new deleteCourseAsyncTask(dao).execute(course);
    }

    public void deleteAllData(){
        new deleteAllDataAsyncTask(dao).execute();
    }

    public LiveData<List<CourseEntity>> getAllCourse(){
        return dao.getAllCourses();
    }
    private static class insertCourseAsyncTask extends AsyncTask<CourseEntity, Void, Void> {
        CourseDAO dao;
        insertCourseAsyncTask(CourseDAO dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseEntity... course){
            dao.insert(course[0]);
            return null;
        }
    }

private static class deleteCourseAsyncTask extends AsyncTask<CourseEntity, Void, Void> {
        CourseDAO dao;
        deleteCourseAsyncTask(CourseDAO dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseEntity... course){
            dao.delete(course[0]);
            return null;
        }
    }

    public void updateCourse(CourseEntity courseEntity){
        new UpdateCourseAsyncTask(dao).execute(courseEntity);
    }
private static class UpdateCourseAsyncTask extends AsyncTask<CourseEntity, Void, Void>{
        CourseDAO dao;
        UpdateCourseAsyncTask(CourseDAO DAO){
            this.dao = DAO;
        }
        protected Void doInBackground(CourseEntity... courseEntities){
            dao.updateCourse(courseEntities[0]);
            return null;
        }
    }


private static class deleteAllDataAsyncTask extends AsyncTask<Void, Void, Void> {
        CourseDAO dao;
        deleteAllDataAsyncTask(CourseDAO dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            dao.deleteAllCourses();
            return null;
        }
    }
}
