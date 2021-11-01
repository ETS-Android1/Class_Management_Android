package com.example.class_management_android.model;

public class Student
{
    private String id;
    private String name;
    private String birthday;
    private int gender;
    private String class_id;
    private String phone_number;
    private String email;

    public Student()
    {

    }

    public Student(String id, String name, String birthday, int gender, String class_id, String phone_number, String email)
    {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.class_id = class_id;
        this.phone_number = phone_number;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getClassID()
    {
        return this.class_id;
    }

    public void setClassID(String class_id)
    {
        this.class_id = class_id;
    }

    public String getPhoneNumber()
    {
        return this.phone_number;
    }

    public void setPhoneNumber(String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
