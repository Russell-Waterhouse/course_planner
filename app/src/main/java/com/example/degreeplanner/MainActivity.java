package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;
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

import com.example.degreeplanner.Adapter.ScheduledCourseArrayAdapter;
import com.example.degreeplanner.Adapter.UnscheduledCourseArrayAdapter;
import com.example.degreeplanner.Database.CourseEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;
    UnscheduledCourseArrayAdapter bottomAdapter;
    boolean courseIsSelected = false;
    boolean isScheduledCourseSelected = false;
    private int selectedCoursePosition;
    View selectedCourseView;
    Button editButton;
    Button deleteButton;
    Guideline middleLine;
    ListView bottomListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        middleLine = findViewById(R.id.divider_guideline);
        init();
    }

    private void init() {
        initLists();
        initButtons();
        initBottomListView();
        initSemesters();
    }

    private void initLists() {
        viewModel.getAllCourses().observe(this, new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(List<CourseEntity> courseEntities) {
                viewModel.setmCourseList(courseEntities);
                updateLists();
                updateViews();
            }
        });
    }

    private void updateLists() {
        viewModel.getUnscheduledCourses().clear();
        viewModel.getScheduledCourses().clear();
        for (CourseEntity course : viewModel.getmCourseList()) {
            if (course.getScheduledYear() == 0 || course.getScheduledSemester() == 'z')
                viewModel.getUnscheduledCourses().add(course);
            else
                viewModel.getScheduledCourses().add(course);
        }
        if(viewModel.getUnscheduledCourses().size()==0){
            //TODO: animate this later on in a future version
            expandBottomListView();
        }
        else{
            collapseBottomListView();
        }
    }//tested

    private void expandBottomListView() {
        middleLine.setGuidelinePercent(1f);
    }

    private void collapseBottomListView(){
        middleLine.setGuidelinePercent(0.5f);
    }

    private void updateViews() {
        for (ScheduledCourseArrayAdapter adapter : viewModel.getmAdapterList()) {
            adapter.updateDataSet(viewModel.getScheduledCourses());
        }
        bottomAdapter.notifyDataSetChanged();
    }


    private void initBottomListView() {
        bottomListView = findViewById(R.id.bottom_list_view);
        if(viewModel.getUnscheduledCourses().size()==0) {
            expandBottomListView();
        }
        bottomAdapter = new UnscheduledCourseArrayAdapter(this, R.layout.course_layout, viewModel.getUnscheduledCourses());
        bottomListView.setAdapter(bottomAdapter);
        bottomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectUncheduledCourse(view, position);
            }//tested
        });
    }

    private void selectUncheduledCourse(View view, int position) {
        if (courseIsSelected) {
            //if there is a course selected somewhere else, deselect it
            deselectSelectedCourse();
        }
        //select the tapped on course
        courseIsSelected = true;
        isScheduledCourseSelected = false;
        selectedCourseView = view;
        selectedCourseView.setBackground(getDrawable(R.drawable.background_accent_rounded_corners));
        selectedCoursePosition = position;
        deleteButton.setVisibility(View.VISIBLE);
        editButton.setVisibility(View.VISIBLE);
    }

    private void deselectSelectedCourse() {
        int deselectedCourseDrawableResource = R.drawable.background_primary_rounded_corners;
        selectedCourseView.setBackground(getDrawable(deselectedCourseDrawableResource));
    }

    private void initButtons() {
        editButton = findViewById(R.id.edit_button);
        deleteButton = findViewById(R.id.delete_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(courseIsSelected) {
                    //go  to the editing activity
                    //todo: figure out how to send information from the currently selected course to the activity
                    Intent intent = new Intent(MainActivity.this, CreateNewCourses.class);
                    CourseEntity course = null;
                    if(isScheduledCourseSelected) {
                        course = viewModel.getScheduledCourses().get(selectedCoursePosition);
                    }
                    else {
                        course = viewModel.getUnscheduledCourses().get(selectedCoursePosition);
                    }
                    intent.putExtra("course to edit: ", course);
                    startActivity(intent);
                    deselectSelectedCourse();
                    courseIsSelected = false;
                    editButton.setVisibility(View.INVISIBLE);
                    deleteButton.setVisibility(View.INVISIBLE);
                }
                else{
                    deleteButton.setVisibility(View.INVISIBLE);
                    editButton.setVisibility(View.INVISIBLE);
                }

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!courseIsSelected){
                    deleteButton.setVisibility(View.INVISIBLE);
                    editButton.setVisibility(View.INVISIBLE);
                }
                else{
                    if(!isScheduledCourseSelected){
                        viewModel.remove(viewModel.getUnscheduledCourses().get(selectedCoursePosition));
                    }
                    else{
                        viewModel.remove((viewModel.getScheduledCourses().get(selectedCoursePosition)));
                    }
                    deleteButton.setVisibility(View.INVISIBLE);
                    editButton.setVisibility(View.INVISIBLE);
                    deselectSelectedCourse();
                    isScheduledCourseSelected = false;
                    courseIsSelected = false;
                    selectedCoursePosition = 0;
                    selectedCourseView = null;
                }
            }
        });
    }


    public void createCourses(View v) {
        Intent startCreatingCourses = new Intent(this, CreateNewCourses.class);
        startActivity(startCreatingCourses);
    }


    private void initSemesters() {
        LinearLayout parentLayout = findViewById(R.id.viewgroup_parent);

        for (int i = 1; i < 8; i++) {
            //init fall layout
            initSemesterLayout(parentLayout, i, R.string.fall, R.color.fall_orange, 'A');
            //init spring layout
            initSemesterLayout(parentLayout, i, R.string.spring, R.color.spring_green, 'B');
            //init summer layout
            initSemesterLayout(parentLayout, i, R.string.summer, R.color.summer_yellow, 'C');
        }
    }

    private void initSemesterLayout(LinearLayout parentLayout, int i, int p, int p2, char b) {
        View springLayout = View.inflate(this, R.layout.semester_layout, null);
        TextView springName = springLayout.findViewById(R.id.semester_name);
        String springSemesterName = getString(p) + " " + i;
        springName.setText(springSemesterName);
        springName.setBackgroundColor(getColor(p2));
        ListView springList = springLayout.findViewById(R.id.semester_list_view);
        ScheduledCourseArrayAdapter springAdapter = new ScheduledCourseArrayAdapter(getApplicationContext(), R.layout.course_layout, viewModel.getScheduledCourses(), i, b);
        viewModel.getmAdapterList().add(springAdapter);
        springList.setAdapter(springAdapter);
        springList.setOnItemClickListener(getOnItemClickListener(i, b, springList, springAdapter));
        parentLayout.addView(springLayout);
    }


    private AdapterView.OnItemClickListener getOnItemClickListener(final int scheduledYear, final char scheduledSemester, final ListView currentList, final ScheduledCourseArrayAdapter currentAdapter) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //there is a course somewhere selected
                if (courseIsSelected) {
                    //an unscheduled course is selected, move it here
                    if (!isScheduledCourseSelected) {
                        if (selectedCourseView != null) {
                            courseIsSelected = false;
                            deselectSelectedCourse();
                            CourseEntity movingCourse = viewModel.getUnscheduledCourses().get(selectedCoursePosition);
                            movingCourse.setScheduledSemester(scheduledSemester);
                            movingCourse.setScheduledYear(scheduledYear);
                            viewModel.updateCourse(movingCourse);
                            deleteButton.setVisibility(View.INVISIBLE);
                            editButton.setVisibility(View.INVISIBLE);
                        }
                    }
                    //a scheduled course is selected, move it here
                    else{
                        if(selectedCourseView != null){
                            courseIsSelected = false;
                            isScheduledCourseSelected = false;
                            deselectSelectedCourse();
                            CourseEntity movingCourse = viewModel.getScheduledCourses().get(selectedCoursePosition);
                            movingCourse.setScheduledSemester(scheduledSemester);
                            movingCourse.setScheduledYear(scheduledYear);
                            viewModel.updateCourse(movingCourse);
                            deleteButton.setVisibility(View.INVISIBLE);
                            editButton.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                //there is no course selected, select this one
                else {
                    //there are courses here
                    if(!parent.getAdapter().isEmpty()) {
                        courseIsSelected = true;
                        selectedCourseView = view;
                        selectedCourseView.setBackground(getDrawable(R.drawable.background_accent_rounded_corners));
                        isScheduledCourseSelected = true;
                        selectedCoursePosition = viewModel.getScheduledCourses().indexOf(currentAdapter.getItem(position));
                        deleteButton.setVisibility(View.VISIBLE);
                        editButton.setVisibility(View.VISIBLE);
//                    Log.d(TAG, "The selected course is: " + scheduledCourses.get(position).toString());
                    }
                    //there is just a placeholder "add courses here" button, start the addCoursesActivity
                    else{
                        startActivity(new Intent(MainActivity.this, CreateNewCourses.class));
                    }
                }
            }
        };
    }
}


//    /*
//    todo: the idea right now is that when an item in the bottom ListView gets tapped, it returns the position of that tap
//    to the main activity. when you then tap on another semester's recycler view, this then removes that item from the array
//    of un scheduled courses, adds it to the array of scheduled courses, and enters the scheduled time into the course entity
//
//    the list of scheduled courses is fed to a ListView for the semester, which is dynamically created from one class
//    this ListView adapter is fed the semester and year that it represents in the constructor, and then sorts through the
//    list of scheduled courses to find ones that belong in its display. it then displays them.
//     */