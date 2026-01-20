package com.model;

import java.io.Serializable;

public class Ranking implements Serializable {
    private int id, column, row;
    public Ranking() {}

    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
}
