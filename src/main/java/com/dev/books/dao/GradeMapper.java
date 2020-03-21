package com.dev.books.dao;

import com.dev.books.pojo.Grade;
import com.dev.books.pojo.Profession;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradeMapper {
    List<Grade> findAllGrade();
}
