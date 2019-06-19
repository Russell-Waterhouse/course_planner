/*
Created may 25 by Russell waterhouse
file purpose: database for room database library
last edited by: Russell Waterhouse May 25
 todo: comment
 */

package com.example.degreeplanner.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = CourseEntity.class, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract CourseDAO courseDAO();

    private static Database INSTANCE;

    static Database getDataBase(final Context context){
        if(INSTANCE == null){
            synchronized (Database.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "course_database").build();
                }

            }

        }
        return INSTANCE;
    }
}
