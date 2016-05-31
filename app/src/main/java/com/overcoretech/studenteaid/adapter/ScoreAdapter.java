package com.overcoretech.studenteaid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.overcoretech.studenteaid.R;
import com.overcoretech.studenteaid.template.ScoreTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pakabah on 18/05/16.
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    List<ScoreTemplate> scoreTemplates;

    public ScoreAdapter(List<ScoreTemplate> scoreTemplates)
    {
        this.scoreTemplates = new ArrayList<>();
        this.scoreTemplates.addAll(scoreTemplates);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_score,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScoreTemplate scoreTemplate = scoreTemplates.get(position);

        holder.courseName.setText(scoreTemplate.courseName);
        holder.date.setText(scoreTemplate.date);
        holder.score.setText(scoreTemplate.score);
    }

    @Override
    public int getItemCount() {
        return scoreTemplates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView courseName;
        TextView score;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            courseName = (TextView) itemView.findViewById(R.id.course);
            score = (TextView) itemView.findViewById(R.id.score);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
