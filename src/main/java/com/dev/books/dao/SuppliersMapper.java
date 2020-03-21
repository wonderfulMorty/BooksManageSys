package com.dev.books.dao;

import com.dev.books.pojo.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SuppliersMapper {
    List<Supplier> findAllSupplierByPages(int start,int pageSize);
    List<Supplier> findAllSupplier();
    int addSupplier(Map map);
    int deleteQsById(String id);
    int updateQsById(Map map);
    List<String>findAllQsName();
}
