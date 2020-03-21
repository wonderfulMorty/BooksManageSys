package com.dev.books.service;

import com.dev.books.pojo.StoreIn;
import com.dev.books.pojo.StoreOut;

import java.util.List;
import java.util.Map;

public interface StoreOutService {
    int addReceiveBook(Map map);
    List<StoreOut>findStoreOutByUserId(String userId);
    int updateTeacherBookOut(int book_out,String book_id,String user_id);
    StoreOut findStoreOutById(String id);
    int deleteStoreOutByUidandBid(String bid,String uid);
    int updateTeacherBookBack(int book_out,String book_id,String user_id);
    List<StoreOut> findStooByUidAndBid(String user_id,String book_id);
}
