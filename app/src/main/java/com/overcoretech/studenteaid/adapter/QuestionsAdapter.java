package com.overcoretech.studenteaid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.overcoretech.studenteaid.R;
import com.overcoretech.studenteaid.template.QuestionsTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pakabah on 18/05/16.
 */
public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder>{

    List<QuestionsTemplate> questionsTemplates;

    public QuestionsAdapter(List<QuestionsTemplate> questionsTemplates)
    {
        this.questionsTemplates = new ArrayList<>();
        this.questionsTemplates.addAll(questionsTemplates);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_question,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QuestionsTemplate questionsTemplate = questionsTemplates.get(position);
        holder.QuestionNumber.setText(questionsTemplate.QuestionNumber);
        holder.Question.setText(questionsTemplate.Question);
        holder.FirstQuestion.setText(questionsTemplate.OptionOne);
        holder.SecondQuestion.setText(questionsTemplate.OptionTwo);
        holder.ThirdQuestion.setText(questionsTemplate.OptionThree);
        holder.FourthQuestion.setText(questionsTemplate.OptionFour);
    }

    @Override
    public int getItemCount() {
        return questionsTemplates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView QuestionNumber;
        TextView Question;
        RadioButton FirstQuestion;
        RadioButton SecondQuestion;
        RadioButton ThirdQuestion;
        RadioButton FourthQuestion;

        public ViewHolder(View itemView) {
            super(itemView);

            QuestionNumber = (TextView) itemView.findViewById(R.id.question_number);
            Question = (TextView) itemView.findViewById(R.id.question);

            FirstQuestion = (RadioButton) itemView.findViewById(R.id.radioButton);
            SecondQuestion = (RadioButton) itemView.findViewById(R.id.radioButton2);
            ThirdQuestion = (RadioButton) itemView.findViewById(R.id.radioButton3);
            FourthQuestion = (RadioButton) itemView.findViewById(R.id.radioButton4);

        }
    }
}
