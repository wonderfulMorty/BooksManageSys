package com.dev.books.dao;

import com.dev.books.pojo.College;
import com.dev.books.pojo.StoreIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface StoreInMapper {
    List<StoreIn> findAllStoreIn();
    List<StoreIn> findAllStoreInByPage(int start,int pageSize);
    /*int  insertStoreIn(
            @Param("id") String id,@Param("store_time") Date store_time,
            @Param("store_info") String store_info,@Param("book_count")int book_count,
            @Param("book_name")String book_name
    );*/
    int insertStoreIn(Map map);
    StoreIn findStoreInById(String id);
    int updateBookCount(int book_count,String id);
    int deleteStoreIn(String id);
    List<StoreIn> findStoreInPercent(String book_kind);
    List<StoreIn>findStoreInByBookId(String book_id);
    int updateBookCountByBookId(int book_count,String bookId);
}
