package com.pojo;

import java.util.List;

public class EmployeeComplex {

    private final int id;
    private final String first_name;
    private final String last_name;
    private final String email;
    private final List<String> job;
    private final List<Marks> marks;
    private final Favfood favfood;

    public EmployeeComplex(int id, String first_name, String last_name, String email, List<String> job, List<Marks> marks, Favfood favfood) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.job = job;
        this.marks = marks;
        this.favfood = favfood;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getJob() {
        return job;
    }

    public List<Marks> getMarks() {
        return marks;
    }

    public Favfood getFavfood() {
        return favfood;
    }

}
