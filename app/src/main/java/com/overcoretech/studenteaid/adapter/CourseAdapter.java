package com.overcoretech.studenteaid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.overcoretech.studenteaid.R;
import com.overcoretech.studenteaid.template.CourseTemplate;
import com.overcoretech.studenteaid.views.DetailPasco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pakabah on 17/05/16.
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{

    List<CourseTemplate> courseTemplates;
    Context context;

    public CourseAdapter(List<CourseTemplate> courseTemplates, Context context)
    {
        this.courseTemplates = new ArrayList<>();
        this.courseTemplates.addAll(courseTemplates);
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_course,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CourseTemplate courseTemplate = courseTemplates.get(position);
        holder.CourseName.setText(courseTemplate.courseName);
        holder.Year.setText(courseTemplate.year);
        holder.CourseName.setTag(courseTemplate.courseCode);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPasco.class);
                intent.putExtra("code", holder.CourseName.getTag().toString());
                intent.putExtra("title",holder.CourseName.getText().toString());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseTemplates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView Year;
        TextView CourseName;
        CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            Year = (TextView) itemView.findViewById(R.id.year);
            CourseName = (TextView) itemView.findViewById(R.id.courseName);
            cardView = (CardView) itemView.findViewById(R.id.courseCard);

        }
    }
}
