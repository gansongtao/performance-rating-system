package com.model;

import java.io.Serializable;

public class StudentScore implements Serializable {
    private int score, credit;
    private String course;
    public StudentScore(int score, int credit, String course) {
        this.score = score;
        this.credit = credit;
        this.course = course;
    }
    public StudentScore() {}

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
}
