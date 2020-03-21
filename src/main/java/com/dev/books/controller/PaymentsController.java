package com.dev.books.controller;

import com.alibaba.fastjson.JSON;
import com.dev.books.pojo.Book;
import com.dev.books.pojo.Payments;
import com.dev.books.pojo.StoreOut;
import com.dev.books.pojo.User;
import com.dev.books.service.BookService;
import com.dev.books.service.PaymentsService;
import com.dev.books.service.StoreOutService;
import com.dev.books.util.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class PaymentsController {
    @Autowired
    StoreOutService storeOutService;
    @Autowired
    PaymentsService paymentsService;
    @Autowired
    BookService bookService;
    @ResponseBody
    @RequestMapping("/payments")
    public String payments(@RequestParam("stoo_id")String stoo_id){
        StoreOut storeOut = storeOutService.findStoreOutById(stoo_id);
        String id = storeOut.getBook_id();
        Book book = bookService.findBookById(id);
        double book_price = book.getBook_price();
        int total = storeOut.getBook_out()-storeOut.getBook_back();
        double payment = total*book_price;
        int n = paymentsService.insertPayments(stoo_id,payment,new Date());
        if(n>0){
            return  "success";
        }
        return "failure";
    }

    @ResponseBody
    @RequestMapping(value = "/payments",produces="application/json;charset=UTF-8")
    public String historyPay(HttpServletRequest request){
        HttpSession session = request.getSession(true);//新建session对象
        User user = (User) session.getAttribute("user");  //将对应数据存入session中
        String user_id = user.getId();
        List<Payments> payments = paymentsService.historyPay(user_id);
        System.out.println(payments);
        Layui l = Layui.data(payments.size(), payments);
        String result = JSON.toJSONString(l);
        System.out.println("result:"+result);
        return  result;
    }

    @ResponseBody
    @RequestMapping(value = "/allHistoryPay",produces="application/json;charset=UTF-8")
    public String allHistoryPay(){
        List<Payments> payments = paymentsService.allHistoryPay();
        System.out.println(payments);
        Layui l = Layui.data(payments.size(), payments);
        String result = JSON.toJSONString(l);
        System.out.println("result:"+result);
        return  result;
    }
}
