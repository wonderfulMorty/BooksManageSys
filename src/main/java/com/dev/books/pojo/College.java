package com.dev.books.pojo;

public class College {
    private int id;
    private String col_name;

    public College() {
    }

    public College(int id, String col_name) {
        this.id = id;
        this.col_name = col_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCol_name() {
        return col_name;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", col_name='" + col_name + '\'' +
                '}';
    }
}
