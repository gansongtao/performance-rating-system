package com.model;

import java.io.InputStream;
import java.io.Serializable;

public class Proj implements Serializable {
    private int id, depid, userid;
    private String name;
    private InputStream doc;
    public Proj(int id, int depid, int userid, String name, InputStream doc) {
        this.id = id;
        this.depid = depid;
        this.userid = userid;
        this.name = name;
        this.doc = doc;
    }
    public Proj() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDepid() {
        return depid;
    }
    public void setDepid(int depid) {
        this.depid = depid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public InputStream getDoc() {
        return doc;
    }
    public void setDoc(InputStream doc) {
        this.doc = doc;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
}
