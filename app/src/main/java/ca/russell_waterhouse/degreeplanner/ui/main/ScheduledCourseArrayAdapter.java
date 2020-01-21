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

import java.util.ArrayList;
import java.util.List;

import ca.russell_waterhouse.degreeplanner.R;
import ca.russell_waterhouse.degreeplanner.database.CourseEntity;

public class ScheduledCourseArrayAdapter extends ArrayAdapter<CourseEntity>{
    private static final String TAG = "ScheduledCourseArrayAda";

    private Context mContext;
    private ArrayList<CourseEntity> thisSemesterCourses;
    private int mYear;
    private char mSemester;
    private boolean mIsEmpty = false;

    ScheduledCourseArrayAdapter(@NonNull Context context, int resource, @NonNull List<CourseEntity> courses, int year, char semester){
        super(context, resource);
        mYear = year;
        mSemester = semester;
        mContext = context;
        thisSemesterCourses = new ArrayList<>();
        initThisSemesterCourses(courses, year, semester);
    }

    private void initThisSemesterCourses(List<CourseEntity> courses, int year, char semester){
        thisSemesterCourses.clear();
        for(CourseEntity course: courses){
            if(course.getScheduledYear()==year && course.getScheduledSemester()==semester)
                thisSemesterCourses.add(course);
        }
        if(thisSemesterCourses.isEmpty()) {
            thisSemesterCourses.add(new CourseEntity(mContext.getString(R.string.add_course), null, null, null, true, true, true, false, 0.00, "z", 0, 'z'));
            mIsEmpty = true;
        }
        else{
            mIsEmpty = false;
        }
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

    @Override
    public int getCount() {
        return thisSemesterCourses.size();
    }

    @Nullable
    @Override
    public CourseEntity getItem(int position) {
        return thisSemesterCourses.get(position);
    }

    void updateDataSet(@NonNull List<CourseEntity> courses){
        initThisSemesterCourses(courses, mYear, mSemester);
        notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty() {
        return mIsEmpty;
    }
}
