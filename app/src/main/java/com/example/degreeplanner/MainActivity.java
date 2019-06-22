package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.degreeplanner.Adapter.CourseArrayAdapter;
import com.example.degreeplanner.Adapter.ScheduledCourseArrayAdapter;
import com.example.degreeplanner.Database.CourseEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
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
//        fall_1_ListView = findViewById(R.id.fall_1);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        unscheduledCourses = new ArrayList<>();
        scheduledCourses = new ArrayList<>();
        LiveData<List<CourseEntity>> courseListLive = viewModel.getAllCourses();
        courseListLive.observe(this, new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(List<CourseEntity> courseEntities) {
                courseList = courseEntities;
//                scheduledCourses.clear();
                unscheduledCourses.clear();
                for(CourseEntity course: courseList){
                    if(course.getScheduledSemester()=='z' && course.getScheduledYear()==0)
                        unscheduledCourses.add(course);
                    else
                        scheduledCourses.add(course);
                }
                Log.d(TAG, "Scheduled courses is: " + scheduledCourses.toString());
//                Log.d(TAG, "unScheduled courses is: " + unscheduledCourses.toString());
//                Log.d(TAG, "courses is: " + courseList.toString());

                initSemesters();
                initListView();
            }
        });
        initSemesters();
        initListView();
    }

    public void createCourses(View v){
        Intent startCreatingCourses = new Intent(this, CreateNewCourses.class);
        startActivity(startCreatingCourses);
//        startCreatingCourses
    }


    private void initSemesters(){
        LinearLayout parentLayout = findViewById(R.id.viewgroup_parent);
        parentLayout.removeAllViews();

        for(int i = 1; i<8; i++){
            //fall layout
            View fallLayout = View.inflate(this, R.layout.semester_layout, null);
//            Log.d(TAG, "inflating the fall layout");
            TextView fallName = fallLayout.findViewById(R.id.semester_name);
            String semesterName = getString(R.string.fall) + " " +i ;
            fallName.setText(semesterName);
            ListView springList = fallLayout.findViewById(R.id.semester_list_view);
            Log.d(TAG, "CourseList right before fall adapter is : "+ scheduledCourses.toString());
            ScheduledCourseArrayAdapter fallAdapter = new ScheduledCourseArrayAdapter(getApplicationContext(), R.layout.course_layout, scheduledCourses, i, 'A');
            springList.setAdapter(fallAdapter);
            final int scheduledYear = i;
            springList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(selectedListView != null) {
                        if (selectedListView.equals(bottomListView)) {
                            CourseEntity movingCourse = unscheduledCourses.get(selectedCoursePosition);
//                            unscheduledCourses.remove(movingCourse);//todo: see if this is redundant
//                            scheduledAdapter.notifyDataSetChanged();
//                            adapter.notifyDataSetChanged();
                            movingCourse.setScheduledSemester('A');
                            movingCourse.setScheduledYear(scheduledYear);
                            viewModel.insertCourse(movingCourse);
//                    scheduledCourses.add(movingCourse);
//                    bottomListView.setAdapter(adapter);
//                    fallListView1.setAdapter(scheduledAdapter);
                        }
                    }
                }
            });
            parentLayout.addView(fallLayout);

            View springLayout = View.inflate(this, R.layout.semester_layout, null);
//            Log.d(TAG, "inflating the spring layout");
            TextView springName = springLayout.findViewById(R.id.semester_name);
            String springSemesterName = getString(R.string.spring) + " " +i ;
            springName.setText(springSemesterName);
            springName.setBackgroundColor(getColor(R.color.spring_green));
            parentLayout.addView(springLayout);

            View summerLayout = View.inflate(this, R.layout.semester_layout, null);
//            parentLayout.removeView(summerLayout);
//            Log.d(TAG, "inflating the summer layout");
            TextView summerName = summerLayout.findViewById(R.id.semester_name);
            String summerSemesterName = getString(R.string.summer) + " " +i ;
            summerName.setText(summerSemesterName);
            summerName.setBackgroundColor(getColor(R.color.summer_yellow));
            parentLayout.addView(summerLayout);
        }
    }
    private void initListView(){


        final ListView bottomListView = findViewById(R.id.bottom_list_view);
        final CourseArrayAdapter adapter = new CourseArrayAdapter(getApplicationContext(), R.layout.course_layout, unscheduledCourses);
        bottomListView.setAdapter(adapter);
        bottomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(selectedCourse != null)
                    selectedCourse.setBackground(getDrawable(R.drawable.background_primary_rounded_corners));
                selectedCourse = view;
                selectedCourse.setBackground(getDrawable(R.drawable.background_accent_rounded_corners));
                selectedCoursePosition = position;
                selectedListView = bottomListView;
            }
        });

//        final ListView fallListView1 = findViewById(R.id.fall_1);

        //creates the scheduled semester views


//        final ScheduledCourseArrayAdapter scheduledAdapter = new ScheduledCourseArrayAdapter(getApplicationContext(), R.layout.course_layout, scheduledCourses, 1, 'A');
//        fallListView1.setAdapter(scheduledAdapter);
//        fallListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(selectedListView != null) {
//                    if (selectedListView.equals(bottomListView)) {
//                        CourseEntity movingCourse = unscheduledCourses.get(selectedCoursePosition);
//                        unscheduledCourses.remove(movingCourse);//todo: see if this is redundant
//                        scheduledAdapter.notifyDataSetChanged();
//                        adapter.notifyDataSetChanged();
//                        movingCourse.setScheduledSemester('A');
//                        movingCourse.setScheduledYear(1);
//                        viewModel.insertCourse(movingCourse);
////                    scheduledCourses.add(movingCourse);
////                    bottomListView.setAdapter(adapter);
////                    fallListView1.setAdapter(scheduledAdapter);
//                    }
//                }
//            }
//        });
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
