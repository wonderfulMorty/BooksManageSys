package com.dev.books.service;

import com.dev.books.pojo.Book;
import com.dev.books.pojo.Supplier;

import java.util.List;
import java.util.Map;

public interface BookService {
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
