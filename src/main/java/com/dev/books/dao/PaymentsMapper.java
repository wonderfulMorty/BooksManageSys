package com.dev.books.dao;

import com.dev.books.pojo.College;
import com.dev.books.pojo.Payments;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface PaymentsMapper {
    int insertPayments(String stoo_id, double payment, Date pay_time);
    List<Payments>historyPay(String user_id);
    List<Payments>allHistoryPay();
}
