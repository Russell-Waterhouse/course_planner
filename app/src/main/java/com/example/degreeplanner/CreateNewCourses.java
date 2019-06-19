package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.degreeplanner.Database.CourseEntity;

public class CreateNewCourses extends AppCompatActivity {
    CreateNewCoursesViewmodel viewmodel;
    CheckBox fall;
    CheckBox spring;
    CheckBox summer;
    EditText courseName;
    EditText prereq1;
    EditText prereq2;
    EditText prereq3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_courses);
        viewmodel  = ViewModelProviders.of(this).get(CreateNewCoursesViewmodel.class);
        initFields();

    }

    private void initFields(){
        fall = findViewById(R.id.checkBoxFall);
        spring = findViewById(R.id.checkBoxSpring);
        summer = findViewById(R.id.checkBoxSummer);
        courseName = findViewById(R.id.course_id);
        prereq1 = findViewById(R.id.prereq_1);
        prereq2 = findViewById(R.id.prereq_2);
        prereq3 = findViewById(R.id.prereq_3);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_another_course_button:
                saveCourse();
                resetActivity();
                break;
            case R.id.done:
                finish();
                break;
                default:
                    break;
        }
    }

    private void saveCourse(){
        //todo: do this through the view model later
        CourseEntity course = new CourseEntity(courseName.getText().toString(), prereq1.getText().toString(), prereq2.getText().toString(), prereq3.getText().toString(), fall.isChecked(), spring.isChecked(), summer.isChecked(), false, 0.00, null, 0, 'z');
        viewmodel.insertCourse(course);
    }
    private void resetActivity(){
        courseName.setText(null);
        prereq1.setText(null);
        prereq2.setText(null);
        prereq3.setText(null);
        fall.setChecked(false);
        spring.setChecked(false);
        summer.setChecked(false);
    }
}
