package com.dev.books.service.iml;

import com.dev.books.dao.StoreOutMapper;
import com.dev.books.pojo.StoreOut;
import com.dev.books.service.StoreOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StoreOutServiceImpl implements StoreOutService {
    @Autowired
    StoreOutMapper storeOutMapper;
    @Override
    public int addReceiveBook(Map map) {
        return storeOutMapper.addReceiveBook(map);
    }

    @Override
    public List<StoreOut> findStoreOutByUserId(String userId) {
        return storeOutMapper.findStoreOutByUserId(userId);
    }

    @Override
    public int updateTeacherBookOut(int book_out, String book_id, String user_id) {
        return storeOutMapper.updateTeacherBookOut(book_out, book_id, user_id);
    }

    @Override
    public StoreOut findStoreOutById(String id) {
        return storeOutMapper.findStoreOutById(id);
    }

    @Override
    public int deleteStoreOutByUidandBid(String bid, String uid) {
        return storeOutMapper.deleteStoreOutByUidandBid(bid, uid);
    }


    @Override
    public int updateTeacherBookBack(int book_out, String book_id, String user_id) {
        return storeOutMapper.updateTeacherBookBack(book_out, book_id, user_id);
    }

    @Override
    public List<StoreOut> findStooByUidAndBid(String user_id, String book_id) {
        return storeOutMapper.findStooByUidAndBid(user_id, book_id);
    }
}
