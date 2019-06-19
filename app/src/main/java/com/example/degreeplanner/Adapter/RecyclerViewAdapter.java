package com.example.degreeplanner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplanner.Database.CourseEntity;
import com.example.degreeplanner.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<CourseEntity> courseList;
    private Context context;

    public RecyclerViewAdapter(List<CourseEntity> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(courseList!=null) {
            holder.courseName.setText(courseList.get(position).getCourseName());
            holder.prereq1.setText(courseList.get(position).getPrereq1Str());
            holder.prereq2.setText(courseList.get(position).getPrereq2Str());
            holder.prereq3.setText(courseList.get(position).getPrereq3Str());
            if(!courseList.get(position).isOfferedInFall()){
                holder.fall.setVisibility(View.INVISIBLE);
            }
            if(!courseList.get(position).isOfferedInSpring()){
                holder.spring.setVisibility(View.INVISIBLE);
            }
            if(!courseList.get(position).isOfferedInSummer()){
                holder.summer.setVisibility(View.INVISIBLE);
            }
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "tapped on a list item", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView courseName;
        private LinearLayout fall;
        private LinearLayout spring;
        private LinearLayout summer;
        private TextView prereq1;
        private TextView prereq2;
        private TextView prereq3;
        private RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_name);
            fall = itemView.findViewById(R.id.offered_in_fall);
            spring = itemView.findViewById(R.id.offered_in_spring);
            summer= itemView.findViewById(R.id.offered_in_summer);
            prereq1 = itemView.findViewById(R.id.prereq_1);
            prereq2 = itemView.findViewById(R.id.prereq_2);
            prereq3 = itemView.findViewById(R.id.prereq_3);
            parentLayout = itemView.findViewById(R.id.course_layout);
        }
    }
}
