package com.dev.books.dao;

import com.dev.books.pojo.Book;
import com.dev.books.pojo.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
    List<Supplier> findAllBookByPages(int start, int pageSize);
    List<Supplier> findAllBook();
    int addBook(Map map);
    Book findBookById(String id);
    int updateBookById(Map map);
    int deleteBookById(String id);
    List<String>findAllBookKind();
    List<Supplier> findAllBookByBookName(String book_name);
    List<String>findBookNameByQsName(String book_name);
}
