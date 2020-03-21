package com.dev.books.service;

import com.dev.books.pojo.Supplier;

import java.util.List;
import java.util.Map;

public interface SupplierService {
    List<Supplier> findAllSupplierByPages(int start,int pageSize);
    List<Supplier> findAllSupplier();
    int addSupplier(Map map);
    int deleteQsById(String id);
    int updateQsById(Map map);
    List<String>findAllQsName();
}
