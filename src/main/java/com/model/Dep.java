package com.model;

import java.io.Serializable;

public class Dep implements Serializable {
    private int id, projid;
    private String name;
    public Dep(int id, String name, int projid) {
        this.id = id;
        this.name = name;
    }
    public Dep() {}
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getProjid() {
        return projid;
    }
    public void setProjid(int projid) {
        this.projid = projid;
    }
}
