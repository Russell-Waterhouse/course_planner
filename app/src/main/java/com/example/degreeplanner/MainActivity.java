package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.degreeplanner.Adapter.CourseArrayAdapter;
import com.example.degreeplanner.Database.CourseEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainViewModel viewModel;
    List<CourseEntity> courseList;
    ArrayList<CourseEntity> unscheduledCourses;
    ArrayList<CourseEntity> scheduledCourses;
    View selectedCourse;

    private int selectedCoursePosition;
    private ListView selectedListView;
    ListView bottomListView ;
    ListView fall_1_ListView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomListView = findViewById(R.id.bottom_list_view);
        fall_1_ListView = findViewById(R.id.fall_1);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        unscheduledCourses = new ArrayList<>();
        LiveData<List<CourseEntity>> courseListLive = viewModel.getAllCourses();
        courseListLive.observe(this, new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(List<CourseEntity> courseEntities) {
                courseList = courseEntities;
                initListView();
            }
        });

//        bottomListView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedListView = bottomListView;
//            }
//        });

//        fall_1_ListView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!selectedListView.equals(fall_1_ListView)){
//
//                }
//                selectedListView = fall_1_ListView;
//            }
//        });

        initListView();
    }

    public void createCourses(View v){
        Intent startCreatingCourses = new Intent(this, CreateNewCourses.class);
        startActivity(startCreatingCourses);
//        startCreatingCourses
    }

    private void initListView(){
        if(courseList != null) {
            for (CourseEntity course : courseList) {
                if (course.getScheduledYear() == 0) {
                    unscheduledCourses.add(course);
                }
            }
        }
        final ListView bottomListView = findViewById(R.id.bottom_list_view);
        CourseArrayAdapter adapter = new CourseArrayAdapter(getApplicationContext(), R.layout.course_layout, unscheduledCourses);
        bottomListView.setAdapter(adapter);
        bottomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(selectedCourse != null)
                    selectedCourse.setBackground(getDrawable(R.drawable.background_primary_rounded_corners));
                selectedCourse = view;
                selectedCourse.setBackground(getDrawable(R.drawable.background_accent_rounded_corners));
                selectedCoursePosition = position;

            }
        });
    }


    /*
    todo: the idea right now is that when an item in the bottom ListView gets tapped, it returns the position of that tap
    to the main activity. when you then tap on another semester's recycler view, this then removes that item from the array
    of un scheduled courses, adds it to the array of scheduled courses, and enters the scheduled time into the course entity

    the list of scheduled courses is fed to a ListView for the semester, which is dynamically created from one class
    this ListView adapter is fed the semester and year that it represents in the constructor, and then sorts through the
    list of scheduled courses to find ones that belong in its display. it then displays them.
     */



}
