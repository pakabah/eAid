package com.overcoretech.studenteaid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.overcoretech.studenteaid.template.CourseTemplate;
import com.overcoretech.studenteaid.template.NotesTemplate;
import com.overcoretech.studenteaid.template.QuestionsTemplate;
import com.overcoretech.studenteaid.template.ScoreTemplate;

import java.util.ArrayList;

/**
 * Created by pakabah on 18/05/16.
 */
public class DBHelper {
    Eaid eaid;

    public DBHelper(Context context)
    {
        eaid = new Eaid(context);
    }

    public void deleteAllCourses()
    {
        SQLiteDatabase db = eaid.getWritableDatabase();
        db.delete(Eaid.TABLE_COURSES,null,null);
        db.close();
    }

    public long insert(String CourseID, String CourseName, String Lecturer, String Year, String description)
    {
        SQLiteDatabase db = eaid.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Eaid.COLUMN_COURSE_ID, CourseID);
        contentValues.put(Eaid.COLUMN_COURSE_NAME, CourseName);
        contentValues.put(Eaid.COLUMN_LECTURER,Lecturer);
        contentValues.put(Eaid.COLUMN_YEAR, Year);
        contentValues.put(Eaid.COLUMN_DESCRIPTION, description);

        long mid = db.insert(Eaid.TABLE_COURSES,null,contentValues);
        db.close();
        return mid;
    }

    public long insertScore(String courseName, String date, String score)
    {
        SQLiteDatabase db = eaid.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Eaid.COLUMN_SCORE_COURSE_NAME,courseName);
        contentValues.put(Eaid.COLUMN_SCORE_DATE,date);
        contentValues.put(Eaid.COLUMN_ACTUAL_SCORE,score);

        long mid = db.insert(Eaid.TABLE_SCORE,null,contentValues);
        db.close();
        return mid;
    }

    public ArrayList<CourseTemplate> getCourses()
    {
        ArrayList<CourseTemplate> courseTemplates = new ArrayList<>();
        SQLiteDatabase db = eaid.getWritableDatabase();
        String[] columns = {Eaid.COLUMN_COURSE_NAME,Eaid.COLUMN_COURSE_ID,Eaid.COLUMN_YEAR};
        Cursor cursor = db.query(Eaid.TABLE_COURSES,columns,null,null,null,null,null);

        while(cursor.moveToNext())
        {
            int index  = cursor.getColumnIndex(Eaid.COLUMN_COURSE_NAME);
            int index1 = cursor.getColumnIndex(Eaid.COLUMN_COURSE_ID);
            int index2 = cursor.getColumnIndex(Eaid.COLUMN_YEAR);

            CourseTemplate courseTemplate = new CourseTemplate();
            courseTemplate.courseName = cursor.getString(index);
            courseTemplate.year = cursor.getString(index2);
            courseTemplate.courseCode = cursor.getString(index1);

            courseTemplates.add(courseTemplate);
        }

        db.close();
        return courseTemplates;
    }

    public CourseTemplate getCourse(String courseId)
    {
        CourseTemplate courseTemplate = new CourseTemplate();
        SQLiteDatabase db = eaid.getWritableDatabase();
        String[] columns = {Eaid.COLUMN_COURSE_NAME,Eaid.COLUMN_COURSE_ID,Eaid.COLUMN_YEAR,Eaid.COLUMN_DESCRIPTION};
        Cursor cursor = db.query(Eaid.TABLE_COURSES,columns,Eaid.COLUMN_COURSE_ID + "= '"+courseId+ "'",null,null,null,null);

        while(cursor.moveToNext())
        {
            int index  = cursor.getColumnIndex(Eaid.COLUMN_COURSE_NAME);
            int index1 = cursor.getColumnIndex(Eaid.COLUMN_COURSE_ID);
            int index2 = cursor.getColumnIndex(Eaid.COLUMN_YEAR);
            int index3 = cursor.getColumnIndex(Eaid.COLUMN_DESCRIPTION);

            courseTemplate.courseName = cursor.getString(index);
            courseTemplate.year = cursor.getString(index2);
            courseTemplate.courseCode = cursor.getString(index1);
            courseTemplate.description = cursor.getString(index3);
        }

        db.close();
        return courseTemplate;

    }

    public ArrayList<NotesTemplate> getNotes()
    {
        ArrayList<NotesTemplate> notesTemplates = new ArrayList<>();
        SQLiteDatabase db = eaid.getWritableDatabase();
        String[] columns = {Eaid.COLUMN_COURSE_NAME,Eaid.COLUMN_COURSE_ID,Eaid.COLUMN_YEAR,Eaid.COLUMN_LECTURER,Eaid.COLUMN_DESCRIPTION};
        Cursor cursor = db.query(Eaid.TABLE_COURSES,columns,null ,null,null,null,null);
        while(cursor.moveToNext())
        {
            int index  = cursor.getColumnIndex(Eaid.COLUMN_COURSE_ID);
            int index1 = cursor.getColumnIndex(Eaid.COLUMN_COURSE_NAME);
            int index2 = cursor.getColumnIndex(Eaid.COLUMN_YEAR);
            int index3 = cursor.getColumnIndex(Eaid.COLUMN_LECTURER);


            NotesTemplate notesTemplate = new NotesTemplate();
            notesTemplate.lecturer = cursor.getString(index3);
            notesTemplate.notesYear = cursor.getString(index2);
            notesTemplate.title = cursor.getString(index1);
            notesTemplate.courseId = cursor.getString(index);


            notesTemplates.add(notesTemplate);
        }

        db.close();

        return notesTemplates;
    }

    public ArrayList<ScoreTemplate> getScores()
    {
        ArrayList<ScoreTemplate> scoreTemplates = new ArrayList<>();
        SQLiteDatabase db = eaid.getWritableDatabase();
        String[] columns = {Eaid.COLUMN_SCORE_DATE, Eaid.COLUMN_SCORE_COURSE_NAME, Eaid.COLUMN_ACTUAL_SCORE};
        Cursor cursor = db.query(Eaid.TABLE_SCORE, columns,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            int index = cursor.getColumnIndex(Eaid.COLUMN_SCORE_COURSE_NAME);
            int index1 = cursor.getColumnIndex(Eaid.COLUMN_SCORE_DATE);
            int index2 = cursor.getColumnIndex(Eaid.COLUMN_ACTUAL_SCORE);

            ScoreTemplate scoreTemplate = new ScoreTemplate();
            scoreTemplate.date = cursor.getString(index1);
            scoreTemplate.score = cursor.getString(index2);
            scoreTemplate.courseName = cursor.getString(index);

            scoreTemplates.add(scoreTemplate);
        }

        return scoreTemplates;
    }

    public ArrayList<QuestionsTemplate> getQuestions(String CourseId)
    {
        ArrayList<QuestionsTemplate> questionsTemplates = new ArrayList<>();
        SQLiteDatabase db = eaid.getWritableDatabase();
        String[] columns = {Eaid.COLUMN_QUESTION,Eaid.COLUMN_ANSWERFOUR,Eaid.COLUMN_ANSWERTHREE, Eaid.COLUMN_ANSWERTWO, Eaid.COLUMN_ANSWERONE,Eaid.COLUMN_ANSWER,Eaid.COLUMN_QUESTION_NUMBER};
        Cursor cursor = db.query(Eaid.TABLE_QUESTIONS,columns, Eaid.COLUMN_QCODE+ "= '"+ CourseId +"'",null,null,null,null);
        while(cursor.moveToNext())
        {
            int index  = cursor.getColumnIndex(Eaid.COLUMN_QUESTION);
            int index1 = cursor.getColumnIndex(Eaid.COLUMN_ANSWERONE);
            int index2 = cursor.getColumnIndex(Eaid.COLUMN_ANSWERTWO);
            int index3 = cursor.getColumnIndex(Eaid.COLUMN_ANSWERTHREE);
            int index4 = cursor.getColumnIndex(Eaid.COLUMN_ANSWERFOUR);
            int index5 = cursor.getColumnIndex(Eaid.COLUMN_ANSWER);
            int index6 = cursor.getColumnIndex(Eaid.COLUMN_QUESTION_NUMBER);

            QuestionsTemplate questionsTemplate = new QuestionsTemplate();
            questionsTemplate.Question = cursor.getString(index);
            questionsTemplate.OptionOne = cursor.getString(index1);
            questionsTemplate.OptionTwo = cursor.getString(index2);
            questionsTemplate.OptionThree = cursor.getString(index3);
            questionsTemplate.OptionFour = cursor.getString(index4);
            questionsTemplate.Answer = cursor.getString(index5);
            questionsTemplate.QuestionNumber = cursor.getString(index6);

            questionsTemplates.add(questionsTemplate);
        }
        db.close();
        return questionsTemplates;
    }

    public NotesTemplate getNote(String courseId)
    {
        NotesTemplate notesTemplate = new NotesTemplate();
        SQLiteDatabase db = eaid.getWritableDatabase();
        String[] columns = {Eaid.COLUMN_COURSE_NAME,Eaid.COLUMN_COURSE_ID,Eaid.COLUMN_YEAR,Eaid.COLUMN_LECTURER,Eaid.COLUMN_DESCRIPTION};
        Cursor cursor = db.query(Eaid.TABLE_COURSES,columns,Eaid.COLUMN_COURSE_ID + "= '"+courseId+ "'",null,null,null,null);
        while(cursor.moveToNext())
        {
            int index  = cursor.getColumnIndex(Eaid.COLUMN_COURSE_ID);
            int index1 = cursor.getColumnIndex(Eaid.COLUMN_COURSE_NAME);
            int index2 = cursor.getColumnIndex(Eaid.COLUMN_YEAR);
            int index3 = cursor.getColumnIndex(Eaid.COLUMN_LECTURER);
            int index4 = cursor.getColumnIndex(Eaid.COLUMN_DESCRIPTION);

            notesTemplate.lecturer = cursor.getString(index3);
            notesTemplate.notesYear = cursor.getString(index2);
            notesTemplate.title = cursor.getString(index1);
            notesTemplate.courseId = cursor.getString(index);
            notesTemplate.description = cursor.getString(index4);
        }
        db.close();
        return notesTemplate;
    }

    public long insertQuestion(String CourseId, String Question, String answerOne, String answerTwo, String answerThree, String answerFour, String answer, String QuestionID,String QuestionNumber)
    {
        SQLiteDatabase db = eaid.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Eaid.COLUMN_QCODE,CourseId);
        contentValues.put(Eaid.COLUMN_QUESTION,Question);
        contentValues.put(Eaid.COLUMN_ANSWERONE, answerOne);
        contentValues.put(Eaid.COLUMN_ANSWERTWO,answerTwo);
        contentValues.put(Eaid.COLUMN_ANSWERTHREE, answerThree);
        contentValues.put(Eaid.COLUMN_ANSWERFOUR,answerFour);
        contentValues.put(Eaid.COLUMN_ANSWER,answer);
        contentValues.put(Eaid.COLUMN_QUESTION_ID,QuestionID);
        contentValues.put(Eaid.COLUMN_QUESTION_NUMBER,QuestionNumber);

        long mid = db.insert(Eaid.TABLE_QUESTIONS,null,contentValues);
        db.close();
        return mid;
    }



    class Eaid extends SQLiteOpenHelper
    {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "eaid.db";

        public static final String TABLE_COURSES = "courses";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_COURSE_ID = "course_id";
        public static final String COLUMN_COURSE_NAME = "course_name";
        public static final String COLUMN_LECTURER = "lecturer";
        public static final String COLUMN_YEAR = "year";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final String DATABASE_COURSES = "create table "+
                TABLE_COURSES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_COURSE_ID + " text, "
                + COLUMN_COURSE_NAME + " text, "
                + COLUMN_LECTURER + " text, "
                + COLUMN_YEAR + " text, "
                + COLUMN_DESCRIPTION + " text);";

        public static final String TABLE_QUESTIONS = "questions";
        public static final String COLUMN_QID = "_id";
        public static final String COLUMN_QCODE = "code";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWERONE = "answer_one";
        public static final String COLUMN_ANSWERTWO = "answer_two";
        public static final String COLUMN_ANSWERTHREE = "answer_three";
        public static final String COLUMN_ANSWERFOUR = "answer_four";
        public static final String COLUMN_ANSWER = "answer";
        public static final String COLUMN_QUESTION_ID = "q_id";
        public static final String COLUMN_QUESTION_NUMBER = "q_number";

        public static final String DATABASE_QUESTIONS = "create table "+
                TABLE_QUESTIONS + "(" + COLUMN_QID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_QCODE + " text, "
                + COLUMN_QUESTION + " text,"
                + COLUMN_ANSWERONE + " text,"
                + COLUMN_ANSWERTWO + " text,"
                + COLUMN_ANSWERTHREE + " text,"
                + COLUMN_ANSWERFOUR + " text,"
                + COLUMN_QUESTION_NUMBER + " text,"
                + COLUMN_ANSWER + " text,"
                + COLUMN_QUESTION_ID + " text);";

        public static final String TABLE_SCORE = "score";
        public static final String COLUMN_SCORE_ID = "_id";
        public static final String COLUMN_SCORE_COURSE_NAME = "course";
        public static final String COLUMN_SCORE_DATE = "date";
        public static final String COLUMN_ACTUAL_SCORE = "score";

        public static final String DATABASE_SCORE = "create table "+
                TABLE_SCORE + "(" + COLUMN_SCORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SCORE_COURSE_NAME + " text, "
                + COLUMN_SCORE_DATE + " text,"
                + COLUMN_ACTUAL_SCORE + " text);";



        public Eaid(Context context) {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_COURSES);
            db.execSQL(DATABASE_QUESTIONS);
            db.execSQL(DATABASE_SCORE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);

        }
    }
}
