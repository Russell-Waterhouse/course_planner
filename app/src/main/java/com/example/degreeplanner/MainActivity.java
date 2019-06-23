package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    List<CourseEntity> mCourseList;
    ArrayList<CourseEntity> unscheduledCourses;
    ArrayList<CourseEntity> scheduledCourses;
    ArrayList<ScheduledCourseArrayAdapter> mAdapterList;
    Boolean courseIsSelected = false;
    View selectedCourse;

    private int selectedCoursePosition;
    private ListView selectedListView;
    ListView bottomListView ;
    Button deleteButton;
    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomListView = findViewById(R.id.bottom_list_view);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        unscheduledCourses = new ArrayList<>();
        scheduledCourses = new ArrayList<>();
        mAdapterList = new ArrayList<>();
        initButtons();
        viewModel.getAllCourses().observe(this, new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(List<CourseEntity> courseEntities) {
                mCourseList = courseEntities;
                initLists();
                updateViews();
            }
        });

        initSemesters();
        initBottomListView();
    }

    private void initButtons(){
        deleteButton = findViewById(R.id.delete_button);
        editButton = findViewById(R.id.edit_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedListView.equals(bottomListView)){
                    viewModel.remove(unscheduledCourses.get(selectedCoursePosition));
                }
                else
                    viewModel.remove(scheduledCourses.get(selectedCoursePosition));
                selectedCourse = null;
                selectedListView = null;
                selectedCoursePosition = 0;
                deleteButton.setVisibility(View.INVISIBLE);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateNewCourses.class));
                editButton.setVisibility(View.INVISIBLE);
            }
        });

    }


    private void updateViews(){
        initLists();
        for(ScheduledCourseArrayAdapter adapter: mAdapterList){
            adapter.updateDataSet(scheduledCourses);
        }
    }

    private void initLists(){
        if(mCourseList!= null) {
//            Log.d(TAG, "initLists called and the value of mCourseList is: " + mCourseList.toString());
            scheduledCourses.clear();
            unscheduledCourses.clear();
            for (CourseEntity course : mCourseList) {
                if (course.getScheduledSemester() == 'z' && course.getScheduledYear() == 0)
                    unscheduledCourses.add(course);
                else
                    scheduledCourses.add(course);
            }
//            Log.d(TAG, "initLists called and the value of scheduledCourses is: " + scheduledCourses.toString());

        }
    }
    public void createCourses(View v){
        Intent startCreatingCourses = new Intent(this, CreateNewCourses.class);
        startActivity(startCreatingCourses);
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
            ListView fallList = fallLayout.findViewById(R.id.semester_list_view);
            initLists();
            ScheduledCourseArrayAdapter fallAdapter = new ScheduledCourseArrayAdapter(getApplicationContext(), R.layout.course_layout, scheduledCourses, i, 'A');
            mAdapterList.add(fallAdapter);
            fallList.setAdapter(fallAdapter);
            fallList.setOnItemClickListener(getOnItemClickListener(i, 'A', fallList, fallAdapter));
            parentLayout.addView(fallLayout);

            //init spring layouts
            View springLayout = View.inflate(this, R.layout.semester_layout, null);
//            Log.d(TAG, "inflating the spring layout");
            TextView springName = springLayout.findViewById(R.id.semester_name);
            String springSemesterName = getString(R.string.spring) + " " +i ;
            springName.setText(springSemesterName);
            ListView springList = springLayout.findViewById(R.id.semester_list_view);
            initLists();
            ScheduledCourseArrayAdapter springAdapter = new ScheduledCourseArrayAdapter(getApplicationContext(), R.layout.course_layout, scheduledCourses, i, 'B');
            mAdapterList.add(springAdapter);
            springList.setAdapter(springAdapter);
            springList.setOnItemClickListener(getOnItemClickListener(i, 'B', springList, springAdapter));
            springName.setBackgroundColor(getColor(R.color.spring_green));
            parentLayout.addView(springLayout);

            //init summer layouts
            View summerLayout = View.inflate(this, R.layout.semester_layout, null);
//            parentLayout.removeView(summerLayout);
//            Log.d(TAG, "inflating the summer layout");
            TextView summerName = summerLayout.findViewById(R.id.semester_name);
            String summerSemesterName = getString(R.string.summer) + " " +i ;
            summerName.setText(summerSemesterName);
            summerName.setBackgroundColor(getColor(R.color.summer_yellow));
            ListView summerList = summerLayout.findViewById(R.id.semester_list_view);
            initLists();
            ScheduledCourseArrayAdapter summerAdapter = new ScheduledCourseArrayAdapter(getApplicationContext(), R.layout.course_layout, scheduledCourses, i, 'C');
            mAdapterList.add(summerAdapter);
            summerList.setAdapter(summerAdapter);
            summerList.setOnItemClickListener(getOnItemClickListener(i, 'C', summerList, summerAdapter));
            parentLayout.addView(summerLayout);
        }


    }

    private AdapterView.OnItemClickListener getOnItemClickListener(final int scheduledYear, final char scheduledSemester, final ListView currentList, final ScheduledCourseArrayAdapter currentAdapter){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (selectedListView != null) {
                    //an unscheduled course is selected, move it here
                    if (bottomListView.equals(selectedListView) && selectedCourse != null) {
//                        Log.d(TAG, "tap case 1");
                        CourseEntity movingCourse = unscheduledCourses.get(selectedCoursePosition);
                        movingCourse.setScheduledSemester(scheduledSemester);
                        movingCourse.setScheduledYear(scheduledYear);
                        viewModel.insertCourse(movingCourse);
                        selectedCourse = null;
                        selectedListView = null;
                        deleteButton.setVisibility(View.INVISIBLE);
                        editButton.setVisibility(View.INVISIBLE);
                        courseIsSelected = false;
                    }
                    //a scheduled course somewhere else is selected, move it here
                    else if (!selectedListView.equals(currentList) && selectedCourse != null) {
//                        Log.d(TAG, "tap case 2");
                        CourseEntity movingCourse = scheduledCourses.get(selectedCoursePosition);
                        movingCourse.setScheduledYear(scheduledYear);
                        movingCourse.setScheduledSemester(scheduledSemester);
//                        Log.d(TAG, "tap case 2: the moving course is : " + movingCourse.toString());
                        viewModel.insertCourse(movingCourse);
                        selectedCourse.setBackground(getDrawable(R.drawable.background_primary_rounded_corners));
                        selectedCourse = null;
                        selectedListView = null;
                        deleteButton.setVisibility(View.INVISIBLE);
                        editButton.setVisibility(View.INVISIBLE);
                        courseIsSelected = false;
                    }
                    //nothing else is selected, select this course
                } else {
                    selectedCourse = view;
//                    Log.d(TAG, "tap case 3");
                    selectedCourse.setBackground(getDrawable(R.drawable.background_accent_rounded_corners));
                    selectedCoursePosition = scheduledCourses.indexOf(currentAdapter.getItem(position));
                    selectedListView = currentList;
                    deleteButton.setVisibility(View.VISIBLE);
                    editButton.setVisibility(View.VISIBLE);

                }

            }
        };
    }
    private void initBottomListView(){


        final ListView bottomListView = findViewById(R.id.bottom_list_view);
        final CourseArrayAdapter adapter = new CourseArrayAdapter(getApplicationContext(), R.layout.course_layout, unscheduledCourses);
        bottomListView.setAdapter(adapter);
        bottomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(selectedCourse != null)
                    selectedCourse.setBackground(getDrawable(R.drawable.background_primary_rounded_corners));
                courseIsSelected = true;
                selectedCourse = view;
                selectedCourse.setBackground(getDrawable(R.drawable.background_accent_rounded_corners));
                selectedCoursePosition = position;
                selectedListView = bottomListView;
                deleteButton.setVisibility(View.VISIBLE);
                editButton.setVisibility(View.VISIBLE);
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





