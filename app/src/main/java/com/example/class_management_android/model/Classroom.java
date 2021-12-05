package com.example.class_management_android.model;

public class Classroom
{
    private String id;
    private String subject_name;
    private String start_time;
    private String end_time;
    private String classroom_name;
    private String week_day;
    private int total_student;

    public Classroom()
    {

    }

    public Classroom(String id, String subject_name, String start_time, String end_time, String classroom_name, String week_day,int total_student) {
        this.id = id;
        this.subject_name = subject_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.classroom_name = classroom_name;
        this.week_day = week_day;
        this.total_student = total_student;
    }

    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSubjectName()
    {
        return this.subject_name;
    }

    public void setSubjectName(String subject_name)
    {
        this.subject_name = subject_name;
    }

    public String getStartTime()
    {
        return this.start_time;
    }

    public void setStartTime(String start_time)
    {
        this.start_time = start_time;
    }

    public String getEndTime()
    {
        return this.end_time;
    }

    public void setEndTime(String end_time)
    {
        this.end_time = end_time;
    }

    public String getClassroomName()
    {
        return this.classroom_name;
    }

    public void setClassroomName(String classroom_name)
    {
        this.classroom_name = classroom_name;
    }

    public String getWeekDay() { return this.week_day;}

    public void setWeekDay(String week_day) { this.week_day = week_day; }

    public int getTotalStudent()
    {
        return this.total_student;
    }

    public void setTotalStudent(int total_student)
    {
        this.total_student = total_student;
    }
}
