package com.dev.books.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Book {
    @Excel(name = "教材编号", orderNum = "0")
    private String id;
    @Excel(name = "教材名称")
    private String book_name;
    @Excel(name = "教材分类")
    private String book_kind;
    @Excel(name = "教材单价")
    private Double book_price;
    @Excel(name = "供货商名称")
    private String qs_name;

    public Book() {
    }

    public Book(String id, String book_name, String book_kind, Double book_price, String qs_name) {
        this.id = id;
        this.book_name = book_name;
        this.book_kind = book_kind;
        this.book_price = book_price;
        this.qs_name = qs_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_kind() {
        return book_kind;
    }

    public void setBook_kind(String book_kind) {
        this.book_kind = book_kind;
    }

    public Double getBook_price() {
        return book_price;
    }

    public void setBook_price(Double book_price) {
        this.book_price = book_price;
    }

    public String getQs_name() {
        return qs_name;
    }

    public void setQs_name(String qs_name) {
        this.qs_name = qs_name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_kind='" + book_kind + '\'' +
                ", book_price='" + book_price + '\'' +
                ", qs_name='" + qs_name + '\'' +
                '}';
    }
}
