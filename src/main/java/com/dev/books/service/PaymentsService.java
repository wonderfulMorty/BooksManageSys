package com.dev.books.service;

import com.dev.books.pojo.Payments;

import java.util.Date;
import java.util.List;

public interface PaymentsService {
    int insertPayments(String stoo_id, double payment, Date pay_time);
    List<Payments> historyPay(String user_id);
    List<Payments>allHistoryPay();
}
