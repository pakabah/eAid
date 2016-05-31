package com.overcoretech.studenteaid.template;

/**
 * Created by pakabah on 18/05/16.
 */
public class QuestionsTemplate {
    public String QuestionNumber;
    public String Question;
    public String OptionOne;
    public String OptionTwo;
    public String OptionThree;
    public String OptionFour;
    public String Answer;


    public QuestionsTemplate(String QuestionNumber, String Question,String OptionOne, String OptionTwo,String OptionThree,String OptionFour)
    {
        this.QuestionNumber = QuestionNumber;
        this.Question = Question;
        this.OptionOne = OptionOne;
        this.OptionTwo = OptionTwo;
        this.OptionThree = OptionThree;
        this.OptionFour = OptionFour;
    }

    public QuestionsTemplate()
    {

    }
}
