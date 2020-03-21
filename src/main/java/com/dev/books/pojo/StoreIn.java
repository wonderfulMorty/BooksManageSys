package com.dev.books.pojo;





import com.alibaba.fastjson.annotation.JSONField;


import java.util.Date;

public class StoreIn {
    private String id;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date store_time;
    private String store_info;
    private int book_count;
    private String book_id;
    private String book_name;
    private Double book_price;
    private String qs_name;
    private int book_init;
    private Double persent;
    public StoreIn() {
    }
    public StoreIn(String book_name,Double persent) {
        this.book_name = book_name;
        this.persent = persent;
    }
    public StoreIn(String id, Date store_time, String store_info, int book_count, String book_name) {
        this.id = id;
        this.store_time = store_time;
        this.store_info = store_info;
        this.book_count = book_count;
        this.book_name = book_name;
    }
    public StoreIn(String id, Date store_time, String store_info, int book_count,
                   String book_name,String qs_name) {
        this.id = id;
        this.store_time = store_time;
        this.store_info = store_info;
        this.book_count = book_count;
        this.book_name = book_name;
        this.qs_name = qs_name;
    }

    public StoreIn(String id, Date store_time, String store_info, int book_count, String book_id, String book_name, Double book_price, String qs_name) {
        this.id = id;
        this.store_time = store_time;
        this.store_info = store_info;
        this.book_count = book_count;
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_price = book_price;
        this.qs_name = qs_name;
    }

    public StoreIn(String id, Date store_time, String store_info, int book_count,
                   String book_name,String qs_name,int book_init) {
        this.id = id;
        this.store_time = store_time;
        this.store_info = store_info;
        this.book_count = book_count;
        this.book_name = book_name;
        this.qs_name = qs_name;
        this.book_init = book_init;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStore_time() {
        return store_time;
    }

    public void setStore_time(Date store_time) {
        this.store_time = store_time;
    }

    public String getStore_info() {
        return store_info;
    }

    public void setStore_info(String store_info) {
        this.store_info = store_info;
    }

    public int getBook_count() {
        return book_count;
    }

    public void setBook_count(int book_count) {
        this.book_count = book_count;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
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

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getBook_init() {
        return book_init;
    }

    public void setBook_init(int book_init) {
        this.book_init = book_init;
    }

    public Double getPersent() {
        return persent;
    }

    public void setPersent(Double persent) {
        this.persent = persent;
    }

    @Override
    public String toString() {
        return "StoreIn{" +
                "id='" + id + '\'' +
                ", store_time=" + store_time +
                ", store_info='" + store_info + '\'' +
                ", book_count=" + book_count +
                ", book_id='" + book_id + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_price=" + book_price +
                ", qs_name='" + qs_name + '\'' +
                ", book_init=" + book_init +
                ", persent=" + persent +
                '}';
    }
}
