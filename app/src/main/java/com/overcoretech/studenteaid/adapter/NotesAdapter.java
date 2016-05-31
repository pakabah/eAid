package com.overcoretech.studenteaid.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.overcoretech.studenteaid.R;
import com.overcoretech.studenteaid.template.NotesTemplate;
import com.overcoretech.studenteaid.views.DetailNotes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pakabah on 17/05/16.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    List<NotesTemplate> notesTemplates;

    public NotesAdapter(List<NotesTemplate> notesTemplates)
    {
     this.notesTemplates = new ArrayList<>();
        this.notesTemplates.addAll(notesTemplates);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        NotesTemplate notesTemplate = notesTemplates.get(position);
        holder.yearNotes.setText(notesTemplate.notesYear);
        holder.lecturer.setText(notesTemplate.lecturer);
        holder.title.setText(notesTemplate.title);
        holder.title.setTag(notesTemplate.courseId);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.relativeLayout.setBackgroundColor(color);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext().getApplicationContext(), DetailNotes.class);
                intent.putExtra("code", holder.title.getTag().toString());
                intent.putExtra("title", holder.title.getText().toString());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesTemplates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView title;
        TextView yearNotes;
        TextView lecturer;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            lecturer = (TextView) itemView.findViewById(R.id.lecturer);
            yearNotes = (TextView) itemView.findViewById(R.id.notesYear);
            cardView = (CardView) itemView.findViewById(R.id.notesCard);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.headerCol);
        }
    }
}
