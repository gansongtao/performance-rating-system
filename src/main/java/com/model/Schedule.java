package com.model;

import java.io.Serializable;

public class Schedule implements Serializable {
    private String course, year, term;
    private int credit;
    public Schedule(String course, String year, String term, int credit) {
        this.course = course;
        this.year = year;
        this.term = term;
        this.credit = credit;
    }
    public Schedule() {}
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }
}
