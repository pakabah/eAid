package com.overcoretech.studenteaid.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
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
    private int currentScore = 0;
    private Context context;

    public QuestionsAdapter(List<QuestionsTemplate> questionsTemplates, Context context)
    {
        this.questionsTemplates = new ArrayList<>();
        this.questionsTemplates.addAll(questionsTemplates);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_question,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        QuestionsTemplate questionsTemplate = questionsTemplates.get(position);
        holder.QuestionNumber.setText(questionsTemplate.QuestionNumber);
        holder.Question.setText(questionsTemplate.Question);
        holder.FirstQuestion.setText(questionsTemplate.OptionOne);
        holder.SecondQuestion.setText(questionsTemplate.OptionTwo);
        holder.ThirdQuestion.setText(questionsTemplate.OptionThree);
        holder.FourthQuestion.setText(questionsTemplate.OptionFour);
        Log.e("Position", position+"");
        Log.e("Score", currentScore+"");


        holder.FirstQuestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("FirstButton", "isChecked"+ isChecked);

                if(isChecked)
                {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    switch (position)
                {
                    case 0:
                        currentScore = currentScore + 1;
                        Log.e("Score", currentScore+"");

                        editor.putInt("currentScore", currentScore);
                        editor.apply();
                        break;
                    case 1:
                        currentScore = currentScore + 1;
                        Log.e("Score", currentScore+"");
                        editor.putInt("currentScore", currentScore);
                        editor.apply();
                        break;
                    case 2:
                        currentScore = currentScore + 1;
                        Log.e("Score", currentScore+"");
                        editor.putInt("currentScore", currentScore);
                        editor.apply();
                        break;

                    case 3:
                        currentScore = currentScore + 1;
                        Log.e("Score", currentScore+"");
                        editor.putInt("currentScore", currentScore);
                        editor.apply();
                        break;
                    case 4:
                        currentScore = currentScore + 1;
                        Log.e("Score", currentScore+"");
                        editor.putInt("currentScore", currentScore);
                        editor.apply();
                        break;
                }

                    holder.FourthQuestion.setChecked(false);
                    holder.FirstQuestion.setChecked(true);
                    holder.SecondQuestion.setChecked(false);
                    holder.ThirdQuestion.setChecked(false);
                }
                else
                {
//                    holder.FourthQuestion.setChecked(false);
//                    holder.FirstQuestion.setChecked(false);
//                    holder.SecondQuestion.setChecked(false);
//                    holder.ThirdQuestion.setChecked(false);
                }


            }
        });

        holder.SecondQuestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    currentScore = currentScore;

                    SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("currentScore", currentScore);
                    editor.apply();

                    holder.FourthQuestion.setChecked(false);
                    holder.FirstQuestion.setChecked(false);
                    holder.SecondQuestion.setChecked(true);
                    holder.ThirdQuestion.setChecked(false);
                }
                else
                {
//                    holder.FourthQuestion.setChecked(false);
//                    holder.FirstQuestion.setChecked(false);
//                    holder.SecondQuestion.setChecked(false);
//                    holder.ThirdQuestion.setChecked(false);
                }
            }
        });

        holder.ThirdQuestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    currentScore = currentScore;

                    SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("currentScore", currentScore);
                    editor.apply();

                    holder.FourthQuestion.setChecked(false);
                    holder.FirstQuestion.setChecked(false);
                    holder.SecondQuestion.setChecked(false);
                    holder.ThirdQuestion.setChecked(true);
                }
                else
                {
//                    holder.FourthQuestion.setChecked(false);
//                    holder.FirstQuestion.setChecked(false);
//                    holder.SecondQuestion.setChecked(false);
//                    holder.ThirdQuestion.setChecked(false);
                }
            }
        });

        holder.FourthQuestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    currentScore = currentScore;

                    SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("currentScore", currentScore);
                    editor.apply();

                    holder.FourthQuestion.setChecked(true);
                    holder.FirstQuestion.setChecked(false);
                    holder.SecondQuestion.setChecked(false);
                    holder.ThirdQuestion.setChecked(false);
                }
                else
                {
//                    holder.FourthQuestion.setChecked(false);
//                    holder.FirstQuestion.setChecked(false);
//                    holder.SecondQuestion.setChecked(false);
//                    holder.ThirdQuestion.setChecked(false);

                }
            }
        });

        holder.clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentScore = currentScore - 1;

                SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentScore", currentScore);
                editor.apply();

                    holder.FourthQuestion.setChecked(false);
                    holder.FirstQuestion.setChecked(false);
                    holder.SecondQuestion.setChecked(false);
                    holder.ThirdQuestion.setChecked(false);
            }
        });


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
        Button clearAll;

        public ViewHolder(View itemView) {
            super(itemView);

            QuestionNumber = (TextView) itemView.findViewById(R.id.question_number);
            Question = (TextView) itemView.findViewById(R.id.question);

            FirstQuestion = (RadioButton) itemView.findViewById(R.id.radioButton);
            SecondQuestion = (RadioButton) itemView.findViewById(R.id.radioButton2);
            ThirdQuestion = (RadioButton) itemView.findViewById(R.id.radioButton3);
            FourthQuestion = (RadioButton) itemView.findViewById(R.id.radioButton4);
            clearAll = (Button) itemView.findViewById(R.id.button3);


        }
    }
}
