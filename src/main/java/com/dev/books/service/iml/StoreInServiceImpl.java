package com.dev.books.service.iml;

import com.dev.books.dao.StoreInMapper;
import com.dev.books.pojo.StoreIn;
import com.dev.books.service.StoreInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StoreInServiceImpl implements StoreInService {
    @Autowired
    StoreInMapper storeInMapper;

    @Override
    public List<StoreIn> findAllStoreIn() {
        return storeInMapper.findAllStoreIn();
    }

    @Override
    public List<StoreIn> findAllStoreInByPage(int start, int pageSize) {
        return storeInMapper.findAllStoreInByPage(start, pageSize);
    }

    @Override
    public int insertStoreIn(Map map) {
        return storeInMapper.insertStoreIn(map);
    }

    @Override
    public StoreIn findStoreInById(String id) {
        return storeInMapper.findStoreInById(id);
    }

    @Override
    public int updateBookCount(int book_count, String id) {
        return storeInMapper.updateBookCount(book_count,id);
    }

    @Override
    public int deleteStoreIn(String id) {
        return storeInMapper.deleteStoreIn(id);
    }

    @Override
    public List<StoreIn> findStoreInPercent(String book_kind) {
        return storeInMapper.findStoreInPercent(book_kind);
    }

    @Override
    public List<StoreIn> findStoreInByBookId(String book_id) {
        return storeInMapper.findStoreInByBookId(book_id);
    }

    @Override
    public int updateBookCountByBookId(int book_count, String bookId) {
        return storeInMapper.updateBookCountByBookId(book_count,bookId);
    }


}
