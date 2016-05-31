package com.overcoretech.studenteaid.template;

/**
 * Created by pakabah on 18/05/16.
 */
public class ScoreTemplate {

   public String courseName;
   public  String score;
   public  String date;


    public ScoreTemplate(String courseName, String score, String date)
    {
        this.courseName = courseName;
        this.score = score;
        this.date = date;
    }

    public ScoreTemplate()
    {

    }
}
