package com.dev.books.pojo;

public class Profession {
    private int id;
    private String prof_name;

    public Profession() {
    }

    public Profession(int id, String prof_name) {
        this.id = id;
        this.prof_name = prof_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProf_name() {
        return prof_name;
    }

    public void setProf_name(String prof_name) {
        this.prof_name = prof_name;
    }

    @Override
    public String toString() {
        return "Profession{" +
                "id=" + id +
                ", prof_name='" + prof_name + '\'' +
                '}';
    }
}
