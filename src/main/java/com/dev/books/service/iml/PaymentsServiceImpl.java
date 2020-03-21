package com.dev.books.service.iml;

import com.dev.books.dao.PaymentsMapper;
import com.dev.books.pojo.Payments;
import com.dev.books.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentsServiceImpl implements PaymentsService {
    @Autowired
    PaymentsMapper paymentsMapper;

    @Override
    public int insertPayments(String stoo_id, double payment, Date pay_time) {
        return paymentsMapper.insertPayments(stoo_id, payment, pay_time);
    }

    @Override
    public List<Payments> historyPay(String user_id) {
        return paymentsMapper.historyPay(user_id);
    }

    @Override
    public List<Payments> allHistoryPay() {
        return paymentsMapper.allHistoryPay();
    }
}
