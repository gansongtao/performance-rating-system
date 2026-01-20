package com.model;

import java.io.Serializable;

public class Curriculum implements Serializable {
    private String gst_course;
    private int column;
    public Curriculum() {}

    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public String getGst_course() {
        return gst_course;
    }
    public void setGst_course(String gst_course) {
        this.gst_course = gst_course;
    }
}
