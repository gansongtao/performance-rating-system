package com.model;

import java.io.Serializable;

public class Region implements Serializable {
    private int count;
    private String region;
    public Region() {}

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
}
