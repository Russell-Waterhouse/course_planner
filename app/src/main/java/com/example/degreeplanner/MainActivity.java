package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.degreeplanner.Adapter.RecyclerViewAdapter;
import com.example.degreeplanner.Database.CourseEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainViewModel viewModel;
    List<CourseEntity> courseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        LiveData<List<CourseEntity>> courseListLive = viewModel.getAllCourses();
        courseListLive.observe(this, new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(List<CourseEntity> courseEntities) {
                courseList = courseEntities;
                initRecyclerView();
            }
        });

//        initRecyclerView();
    }

    public void createCourses(View v){
        Intent startCreatingCourses = new Intent(this, CreateNewCourses.class);
        startActivity(startCreatingCourses);
//        startCreatingCourses
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.bottom_recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(courseList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
