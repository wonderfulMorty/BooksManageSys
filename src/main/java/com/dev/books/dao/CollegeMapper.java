package com.dev.books.dao;

import com.dev.books.pojo.College;
import com.dev.books.pojo.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CollegeMapper {
    List<College> findAllCollege();
}
