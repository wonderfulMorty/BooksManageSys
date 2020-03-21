package com.dev.books.service.iml;

import com.dev.books.dao.BookMapper;
import com.dev.books.pojo.Book;
import com.dev.books.pojo.Supplier;
import com.dev.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Supplier> findAllBookByPages(int start, int pageSize) {
        return bookMapper.findAllBookByPages(start,pageSize);
    }

    @Override
    public List<Supplier> findAllBook() {
        return bookMapper.findAllBook();
    }

    @Override
    public int addBook(Map map) {
        return bookMapper.addBook(map);
    }

    @Override
    public Book findBookById(String id) {
        return bookMapper.findBookById(id);
    }

    @Override
    public int updateBookById(Map map) {
        return bookMapper.updateBookById(map);
    }

    @Override
    public int deleteBookById(String id) {
        return bookMapper.deleteBookById(id);
    }

    @Override
    public List<String> findAllBookKind() {
        return bookMapper.findAllBookKind();
    }

    @Override
    public List<Supplier> findAllBookByBookName(String book_name) {
        return bookMapper.findAllBookByBookName(book_name);
    }

    @Override
    public List<String> findBookNameByQsName(String book_name) {
        return bookMapper.findBookNameByQsName(book_name);
    }
}
