package com.dev.books.dao;

import com.dev.books.pojo.College;
import com.dev.books.pojo.Profession;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfessionMapper {
    List<Profession> findAllProfession();
}
