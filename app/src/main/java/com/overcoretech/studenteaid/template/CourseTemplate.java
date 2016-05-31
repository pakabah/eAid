package com.overcoretech.studenteaid.template;

/**
 * Created by pakabah on 17/05/16.
 */
public class CourseTemplate {
    public String courseName;
    public String year;
    public String courseCode;
    public String description;

   public CourseTemplate(String courseName, String year)
    {
        this.courseName = courseName;
        this.year = year;
    }

    public CourseTemplate()
    {

    }
}
