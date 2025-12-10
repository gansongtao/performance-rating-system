package com.model;

import java.io.Serializable;

public class Choices implements Serializable {
    private String course, term;
    public Choices(String course, String term) {
        this.course = course;
        this.term = term;
    }
    public Choices() {}

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
}
