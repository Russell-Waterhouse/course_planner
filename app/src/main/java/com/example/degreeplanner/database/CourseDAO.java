/*
Created may 25 by Russell waterhouse
file purpose: Data access object (DAO) for room database library
last edited by: Russell Waterhouse May 25
todo: comment
 */
package com.example.degreeplanner.database;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(CourseEntity course);

    @Delete
    public void delete(CourseEntity course);

    @Query("DELETE FROM Courses")
    public void deleteAllCourses();

    @Query("SELECT * FROM Courses")
    public LiveData<List<CourseEntity>> getAllCourses();

    @Update
    public void updateCourse(CourseEntity course);//todo: implement
}
