package com.dev.books.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Payments {
    private int id;
    private String stoo_id;
    private double payment;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date pay_time;
    private String name;
    private String book_name;
    private double book_price;
    public Payments() {
    }

    public Payments(String stoo_id, double payment, Date pay_time, String name, String book_name, double book_price) {
        this.stoo_id = stoo_id;
        this.payment = payment;
        this.pay_time = pay_time;
        this.name = name;
        this.book_name = book_name;
        this.book_price = book_price;
    }

    public Payments(int id, String stoo_id, double payment, Date pay_time, String name, String book_name, double book_price) {
        this.id = id;
        this.stoo_id = stoo_id;
        this.payment = payment;
        this.pay_time = pay_time;
        this.name = name;
        this.book_name = book_name;
        this.book_price = book_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoo_id() {
        return stoo_id;
    }

    public void setStoo_id(String stoo_id) {
        this.stoo_id = stoo_id;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "id=" + id +
                ", stoo_id='" + stoo_id + '\'' +
                ", payment=" + payment +
                ", pay_time=" + pay_time +
                ", name='" + name + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_price=" + book_price +
                '}';
    }
}
