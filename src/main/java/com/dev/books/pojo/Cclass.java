package com.dev.books.pojo;

public class Cclass {
    private int id;
    private String ccl_name;

    public Cclass() {
    }

    public Cclass(int id, String ccl_name) {
        this.id = id;
        this.ccl_name = ccl_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCcl_name() {
        return ccl_name;
    }

    public void setCcl_name(String ccl_name) {
        this.ccl_name = ccl_name;
    }

    @Override
    public String toString() {
        return "Cclass{" +
                "id=" + id +
                ", ccl_name='" + ccl_name + '\'' +
                '}';
    }
}
