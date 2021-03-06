package ca.russell_waterhouse.degreeplanner.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ca.russell_waterhouse.degreeplanner.R;
import ca.russell_waterhouse.degreeplanner.database.CourseEntity;

public class UnscheduledCourseArrayAdapter extends ArrayAdapter<CourseEntity> {

    UnscheduledCourseArrayAdapter(Context context, int resource, @NonNull List<CourseEntity> courses){
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
            if (!course.isOfferedInFall()) {
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

