package com.model;

import java.io.Serializable;

public class School implements Serializable {
    private int stuid, column;
    public School() {}

    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    public int getStuid() {
        return stuid;
    }
    public void setStuid(int stuid) {
        this.stuid = stuid;
    }
}
