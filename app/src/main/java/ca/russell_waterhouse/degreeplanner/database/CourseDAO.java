package ca.russell_waterhouse.degreeplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {

    /**
     * inserts a courseEntity into the SQLite database
     * @param course the courseEntity to be inserted into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseEntity course);

    /**
     * deletes a course from the database
     * @param course course object containing the data to be deleted from the database
     */
    @Delete
    void delete(CourseEntity course);

    /**
     * deletes all data from the database
     */
    @Query("DELETE FROM Courses")
    void deleteAllCourses();

    /**
     * creates and returns a LiveData list containing all data in the database
     * @return a liveData list of all courses in the database
     */
    @Query("SELECT * FROM Courses")
    LiveData<List<CourseEntity>> getAllCourses();

    /**
     * updates a list in the database
     * @param course the course to be updated
     */
    @Update
    void updateCourse(CourseEntity course);
}
