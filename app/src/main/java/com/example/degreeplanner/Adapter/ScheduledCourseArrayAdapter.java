package com.example.degreeplanner.Adapter;

import android.content.Context;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

public class ScheduledCourseArrayAdapter extends ArrayAdapter<CourseEntity>{
    private static final String TAG = "ScheduledCourseArrayAda";

    private Context mContext;
    private ArrayList<CourseEntity> thisSemesterCourses;
    private int mYear;
    private char mSemester;

    public ScheduledCourseArrayAdapter(@NonNull Context context, int resource, @NonNull List<CourseEntity> courses, int year, char semester){
        super(context, resource);
        mYear = year;
        mSemester = semester;
        mContext = context;
        thisSemesterCourses = new ArrayList<>();
        Log.d(TAG, "The courseList is: " + courses.toString());
        for(CourseEntity course: courses){
            Log.d(TAG, "The course is: "+ course.toString());
            if(course.getScheduledYear()==year && course.getScheduledSemester()==semester)
                thisSemesterCourses.add(course);
        }
        if(thisSemesterCourses.isEmpty())
            thisSemesterCourses.add(new CourseEntity(mContext.getString(R.string.add_course), null, null, null, true, true, true, false, 0.00, "z", 0, 'z'));
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d(TAG, "getView called; thisSemesterCourses: "+ thisSemesterCourses.toString());
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_layout, parent, false);
            Log.d(TAG, "getView called");
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
            if(!course.isOfferedInFall())
                fall.setVisibility(View.INVISIBLE);
            if(!course.isOfferedInSpring())
                spring.setVisibility(View.INVISIBLE);
            if(!course.isOfferedInSummer())
                summer.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return thisSemesterCourses.size();
    }

    @Nullable
    @Override
    public CourseEntity getItem(int position) {
        return thisSemesterCourses.get(position);
    }
}
