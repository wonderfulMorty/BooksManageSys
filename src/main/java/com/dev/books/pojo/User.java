package com.dev.books.pojo;

public class User {
    private String id;
    private String name;
    private String password;
    private String phone;
    private String photo;
    private String email;
    private String identification;
    private int is_admin;
    private String col_name;
    private String prof_name;
    private String ccl_name;
    private String gra_name;


    private String t_id;

    public User() {
    }

    public User(String id, String name, String password, String phone, String email, String identification, int is_admin, String col_name, String prof_name, String ccl_name, String gra_name) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.identification = identification;
        this.is_admin = is_admin;
        this.col_name = col_name;
        this.prof_name = prof_name;
        this.ccl_name = ccl_name;
        this.gra_name = gra_name;
    }

    public User(String id, String name, String identification, String t_id) {
        this.id = id;
        this.name = name;
        this.identification = identification;
        this.t_id = t_id;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public String getCol_name() {
        return col_name;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }

    public String getProf_name() {
        return prof_name;
    }

    public void setProf_name(String prof_name) {
        this.prof_name = prof_name;
    }

    public String getCcl_name() {
        return ccl_name;
    }

    public void setCcl_name(String ccl_name) {
        this.ccl_name = ccl_name;
    }

    public String getGra_name() {
        return gra_name;
    }

    public void setGra_name(String gra_name) {
        this.gra_name = gra_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", identification='" + identification + '\'' +
                ", is_admin=" + is_admin +
                ", col_name='" + col_name + '\'' +
                ", prof_name='" + prof_name + '\'' +
                ", ccl_name='" + ccl_name + '\'' +
                ", gra_name='" + gra_name + '\'' +
                ", t_id='" + t_id + '\'' +
                '}';
    }
}