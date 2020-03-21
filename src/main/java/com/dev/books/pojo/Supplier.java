package com.dev.books.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Supplier {
    @Excel(name = "供应商代码号", orderNum = "0")
    private String id;
    @Excel(name = "供应商名称")
    private String qs_name;
    @Excel(name = "供应商地址")
    private String qs_location;
    @Excel(name = "供应商联系方式")
    private String qs_phone;

    public Supplier() {
    }

    public Supplier(String id, String qs_name, String qs_location, String qs_phone) {
        this.id = id;
        this.qs_name = qs_name;
        this.qs_location = qs_location;
        this.qs_phone = qs_phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQs_name() {
        return qs_name;
    }

    public void setQs_name(String qs_name) {
        this.qs_name = qs_name;
    }

    public String getQs_location() {
        return qs_location;
    }

    public void setQs_location(String qs_location) {
        this.qs_location = qs_location;
    }

    public String getQs_phone() {
        return qs_phone;
    }

    public void setQs_phone(String qs_phone) {
        this.qs_phone = qs_phone;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id='" + id + '\'' +
                ", qs_name='" + qs_name + '\'' +
                ", qs_location='" + qs_location + '\'' +
                ", qs_phone='" + qs_phone + '\'' +
                '}';
    }
}
