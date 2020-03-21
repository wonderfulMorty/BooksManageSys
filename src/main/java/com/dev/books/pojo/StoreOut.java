package com.dev.books.pojo;

public class StoreOut {
    private String id;
    private String stoo_id;
    private String book_id;
    private String user_id;
    private int book_out;
    private int book_back;
    private String book_name;
    private Double book_price;
    private String qs_name;
    private int total;

    public StoreOut() {
    }
    public StoreOut(String id, String book_id, String user_id, int book_out) {
        this.id = id;
        this.book_id = book_id;
        this.user_id = user_id;
        this.book_out = book_out;
    }
    public StoreOut(String id, String book_id, String user_id, int book_out, int book_back) {
        this.id = id;
        this.book_id = book_id;
        this.user_id = user_id;
        this.book_out = book_out;
        this.book_back = book_back;
    }

    public StoreOut(String book_name, Double book_price,String qs_name,int book_out,int book_back,int total) {
        this.book_name = book_name;
        this.book_price = book_price;
        this.qs_name = qs_name;
        this.book_out = book_out;
        this.book_back = book_back;
        this.total = total;
    }
    public StoreOut(String book_name, Double book_price,String qs_name,int book_out,int book_back) {
        this.book_name = book_name;
        this.book_price = book_price;
        this.qs_name = qs_name;
        this.book_out = book_out;
        this.book_back = book_back;
    }

    public StoreOut(String id, String stoo_id, String book_id, String user_id, int book_out, int book_back, String book_name, Double book_price, String qs_name, int total) {
        this.id = id;
        this.stoo_id = stoo_id;
        this.book_id = book_id;
        this.user_id = user_id;
        this.book_out = book_out;
        this.book_back = book_back;
        this.book_name = book_name;
        this.book_price = book_price;
        this.qs_name = qs_name;
        this.total = total;
    }
    public StoreOut(String id, String book_id, String user_id, int book_out, int book_back, String book_name, Double book_price, String qs_name, int total) {
        this.id = id;
        this.book_id = book_id;
        this.user_id = user_id;
        this.book_out = book_out;
        this.book_back = book_back;
        this.book_name = book_name;
        this.book_price = book_price;
        this.qs_name = qs_name;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getBook_out() {
        return book_out;
    }

    public void setBook_out(int book_out) {
        this.book_out = book_out;
    }

    public int getBook_back() {
        return book_back;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setBook_back(int book_back) {
        this.book_back = book_back;
    }

    public String getStoo_id() {
        return stoo_id;
    }

    public void setStoo_id(String stoo_id) {
        this.stoo_id = stoo_id;
    }

    @Override
    public String toString() {
        return "StoreOut{" +
                "id='" + id + '\'' +
                ", stoo_id='" + stoo_id + '\'' +
                ", book_id='" + book_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", book_out=" + book_out +
                ", book_back=" + book_back +
                ", book_name='" + book_name + '\'' +
                ", book_price=" + book_price +
                ", qs_name='" + qs_name + '\'' +
                ", total=" + total +
                '}';
    }
}
