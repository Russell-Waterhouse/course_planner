package com.example.degreeplanner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.degreeplanner.Database.CourseEntity;
import com.example.degreeplanner.R;

import org.w3c.dom.Text;

import java.util.List;

public class UnscheduledCourseArrayAdapter extends ArrayAdapter<CourseEntity> {


    public UnscheduledCourseArrayAdapter(Context context, int resource, @NonNull List<CourseEntity> courses){
        super(context, resource, courses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_layout, parent, false);
        }
        CourseEntity course = this.getItem(position);
        TextView courseName = convertView.findViewById(R.id.course_name);
        LinearLayout fall = convertView.findViewById(R.id.offered_in_fall);
        LinearLayout spring = convertView.findViewById(R.id.offered_in_spring);
        LinearLayout summer= convertView.findViewById(R.id.offered_in_summer);
        TextView prereq1 = convertView.findViewById(R.id.prereq_1);
        TextView prereq2 = convertView.findViewById(R.id.prereq_2);
        TextView prereq3 = convertView.findViewById(R.id.prereq_3);
        if(course != null) {
            courseName.setText(course.getCourseName());
            prereq1.setText(course.getPrereq1Str());
            prereq2.setText(course.getPrereq2Str());
            prereq3.setText(course.getPrereq3Str());
            if (course.isOfferedInFall()) {
                fall.setVisibility(View.INVISIBLE);
            } else {
                fall.setVisibility(View.VISIBLE);
            }
            if (!course.isOfferedInSpring()) {
                spring.setVisibility(View.INVISIBLE);
            } else {
                spring.setVisibility(View.VISIBLE);
            }
            if (!course.isOfferedInSummer()){
                summer.setVisibility(View.INVISIBLE);
            }
            else{
                summer.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }


}

