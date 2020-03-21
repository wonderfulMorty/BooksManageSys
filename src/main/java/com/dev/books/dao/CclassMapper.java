package com.dev.books.dao;

import com.dev.books.pojo.Cclass;
import com.dev.books.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CclassMapper {
    List<Cclass> findAllCclass();
}
