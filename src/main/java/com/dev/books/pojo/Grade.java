package com.dev.books.pojo;

public class Grade {
    private int id;
    private String gra_name;

    public Grade() {
    }

    public Grade(int id, String gra_name) {
        this.id = id;
        this.gra_name = gra_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGra_name() {
        return gra_name;
    }

    public void setGra_name(String gra_name) {
        this.gra_name = gra_name;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", gra_name='" + gra_name + '\'' +
                '}';
    }
}
