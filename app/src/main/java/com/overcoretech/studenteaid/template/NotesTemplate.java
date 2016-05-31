package com.overcoretech.studenteaid.template;

/**
 * Created by pakabah on 17/05/16.
 */
public class NotesTemplate {
    public String notesYear;
    public String title;
    public String lecturer;
    public String courseId;
    public String description;


    public NotesTemplate(String notesYear, String title,String lecturer)
    {
        this.notesYear = notesYear;
        this.title = title;
        this.lecturer = lecturer;
    }

    public NotesTemplate()
    {

    }
}
