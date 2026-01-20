package com.model;

import java.io.Serializable;
import java.sql.Date;

public class Rate implements Serializable {
    private int id;
    private String[] seconds;
    private Date start, ending;
    public Rate(int id, String[] seconds, Date start, Date ending) {
        this.id = id;
        this.seconds = seconds;
        this.start = start;
        this.ending = ending;
    }
    public Rate() {}
    public void setEnd(Date ending) {
        this.ending = ending;
    }
    public void setStart(Date start) {
        this.start = start;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setSeconds(String[] seconds) {
        this.seconds = seconds;
    }
    public Date getStart() {
        return start;
    }
    public Date getEnding() {
        return ending;
    }
    public int getId() {
        return id;
    }
    public String[] getSeconds() {
        return seconds;
    }
}
