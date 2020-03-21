package com.dev.books.controller;

import com.alibaba.fastjson.JSON;
import com.dev.books.pojo.Book;
import com.dev.books.pojo.StoreIn;
import com.dev.books.pojo.StoreOut;
import com.dev.books.pojo.User;
import com.dev.books.service.BookService;
import com.dev.books.service.StoreInService;
import com.dev.books.service.StoreOutService;
import com.dev.books.service.UserService;
import com.dev.books.util.Layui;
import com.dev.books.util.RandNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class StoreOutController {
    @Autowired
    StoreOutService storeOutService;

    @Autowired
    StoreInService storeInService;

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/inserTeachertReceiveBook")
    public String addReceiveBook(@RequestBody Map dataMap, HttpServletRequest request) {
        System.out.println(dataMap);
        String book_id = dataMap.get("book_id").toString();
        String book_out = dataMap.get("book_out").toString();
        String storeInId = dataMap.get("id").toString();
        String id = RandNum.getGUID();
        /*
        要先登录，才有getId
         */
        HttpSession session = request.getSession(true);//新建session对象
        User user = (User) session.getAttribute("user");  //将对应数据存入session中
        StoreOut storeOut = new StoreOut(id,book_id,user.getId(),Integer.parseInt(book_out));
        System.out.println("storeOut:"+storeOut);
        /*
        使用fastjson把类转换成json,有两个好处，
        1.需要map类型传参数
        2.fastjson能够格式化时间类型
         */
        String storeInJson = JSON.toJSONString(storeOut);
        Map map = JSON.parseObject(storeInJson, Map.class);
        System.out.println(map);
        List<StoreOut> storeOuts = storeOutService.findStooByUidAndBid(user.getId(),book_id);
        if(storeOuts.size()>0){
            return "cannot receve";
        }else {
            int n = storeOutService.addReceiveBook(map);
            if (n > 0) {
                StoreIn storeIn = storeInService.findStoreInById(storeInId);
                int init_book_count = storeIn.getBook_count();//原来库存
                int current_book_count = init_book_count-Integer.parseInt(book_out);//现在库存=原来库存-已经领走库存
                storeInService.updateBookCount(current_book_count,storeInId);
                return "success";
            }
            return "failure";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getStoreOutByUserId",produces="application/json;charset=UTF-8")
    public String getStoreOutByUserId(@RequestParam("limit") String limit, @RequestParam("page") String page, HttpServletRequest request) {
        List<StoreOut> list = new ArrayList<>();
        String uid="";
        HttpSession session = request.getSession(true);//新建session对象
        User user = (User) session.getAttribute("user");  //将对应数据存入session中
        User u = null;
        if(user.getIdentification().equals("学生")){
            u = userService.findTeacherByUserId(user.getId());
            uid = u.getT_id();
        }else if(user.getIdentification().equals("教师")){
            uid = user.getId();
        }
        List<StoreOut> storeOuts = storeOutService.findStoreOutByUserId(uid);
        for(int i=0;i<storeOuts.size();i++){
            StoreOut s = storeOuts.get(i);
            int total_add = s.getBook_out();
            int total_sub = s.getBook_back();
            int total = total_add - total_sub;
            s.setTotal(total);
            list.add(s);
        }
        Layui l = Layui.data(list.size(), list);
        return JSON.toJSONString(l);
    }

    @ResponseBody
    @RequestMapping(value = "/getStuStoreOutByUserId",produces="application/json;charset=UTF-8")
    public String getStuStoreOutByUserId(@RequestParam("limit") String limit, @RequestParam("page") String page, HttpServletRequest request) {
        List<StoreOut> list = new ArrayList<>();
        HttpSession session = request.getSession(true);//新建session对象
        User user = (User) session.getAttribute("user");  //将对应数据存入session中
        List<StoreOut> storeOuts = storeOutService.findStoreOutByUserId(user.getId());
        for(int i=0;i<storeOuts.size();i++){
            StoreOut s = storeOuts.get(i);
            int total_add = s.getBook_out();
            int total_sub = s.getBook_back();
            int total = total_add - total_sub;
            s.setTotal(total);
            list.add(s);
        }
        Layui l = Layui.data(list.size(), list);
        System.out.println(list);
        return JSON.toJSONString(l);
    }

    @ResponseBody
    @RequestMapping(value = "/inserStutReceiveBook")
    public String inserStutReceiveBook(@RequestBody Map dataMap, HttpServletRequest request) {
        System.out.println(dataMap);
        //String book_id = dataMap.get("book_id").toString();
        String book_out = dataMap.get("book_out").toString();
        String book_id = dataMap.get("id").toString();
        String id = RandNum.getGUID();
        /*
        要先登录，才有getId
         */
        HttpSession session = request.getSession(true);//新建session对象
        User user = (User) session.getAttribute("user");  //将对应数据存入session中
        StoreOut storeOut = new StoreOut(id,book_id,user.getId(),Integer.parseInt(book_out));
        System.out.println("storeOut:"+storeOut);
        User u = userService.findTeacherByUserId(user.getId());
        /*
        使用fastjson把类转换成json,有两个好处，
        1.需要map类型传参数
        2.fastjson能够格式化时间类型
         */
        String storeInJson = JSON.toJSONString(storeOut);
        Map map = JSON.parseObject(storeInJson, Map.class);
        System.out.println(map);
        System.out.println("book_id:"+book_id);
        List<StoreOut> sto = storeOutService.findStooByUidAndBid(user.getId(),book_id);
        if(sto.size()>0){
            return "cannot receve";
        }else{
            int n = storeOutService.addReceiveBook(map);
            if (n > 0) {
                String tid = u.getT_id();
                List<StoreOut> storeOuts = storeOutService.findStoreOutByUserId(tid);//查询教师id号
                //StoreIn storeIn = storeInService.findStoreInById(storeInId);
                int init_book_count = storeOuts.get(0).getBook_out();//原来教师库存
                int current_book_count = init_book_count-Integer.parseInt(book_out);//现在库存=原来库存-已经领走库存
                storeOutService.updateTeacherBookOut(current_book_count,book_id,tid);
                //storeInService.updateBookCount(current_book_count,storeInId);
                return "success";
            }
            return "failure";
        }

    }

    @ResponseBody
    @RequestMapping(value = "/delete_updateTeachertStore")
    public String delete_updateTeachertStore(@RequestBody Map dataMap, HttpServletRequest request) {
        System.out.println(dataMap);
        System.out.println("----教师退书-------");
        String book_id = dataMap.get("book_id").toString();
        String book_num = dataMap.get("book_num").toString();
        String isAll = dataMap.get("isAll").toString();
        HttpSession session = request.getSession(true);//新建session对象
        User user = (User) session.getAttribute("user");  //将对应数据存入session中
        if(isAll.equals("yes")){//退还该书的全部
            List<StoreOut> stoos = storeOutService.findStooByUidAndBid(user.getId(),book_id);
            int current_teach_book = stoos.get(0).getBook_out();
            int n = storeOutService.deleteStoreOutByUidandBid(book_id,user.getId());
            if(n>0){
                List<StoreIn> storeIns = storeInService.findStoreInByBookId(book_id);
                int current_book_count = storeIns.get(0).getBook_count() + current_teach_book;
                storeInService.updateBookCountByBookId(current_book_count,book_id);
                return "success";
            }else {
                return "failure";
            }
        }else{
            //退还书的一部分
            List<StoreOut> storeOuts = storeOutService.findStooByUidAndBid(user.getId(),book_id);
            if(storeOuts.size()>0&&storeOuts.get(0).getBook_back()!=0){
                return "cannot back";
            }else{
                int n = storeOutService.updateTeacherBookBack(Integer.parseInt(book_num),book_id,user.getId());
                if(n>0){
                    List<StoreIn> storeIns = storeInService.findStoreInByBookId(book_id);
                    int current_book_count = storeIns.get(0).getBook_count() + Integer.parseInt(book_num);
                    storeInService.updateBookCountByBookId(current_book_count,book_id);
                    return "success";
                }else {
                    return "failure";
                }
            }
        }
    }

    /**
     * 教师和学生退书还不一样，教师退书是直接退到书库，学生是退书退到教师手中。
     * @param dataMap
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete_updateStutStore")
    public String delete_updateStutStore(@RequestBody Map dataMap, HttpServletRequest request) {
        System.out.println(dataMap);
        System.out.println("----学生退书-------");
        String book_id = dataMap.get("book_id").toString();
        String book_num = dataMap.get("book_num").toString();
        String isAll = dataMap.get("isAll").toString();
        HttpSession session = request.getSession(true);//新建session对象
        User user = (User) session.getAttribute("user");  //将对应数据存入session中
        User u = userService.findTeacherByUserId(user.getId());
        String uid = u.getT_id();
        List<StoreOut> teachStoos = storeOutService.findStooByUidAndBid(uid,book_id);
        int current_tea_book = teachStoos.get(0).getBook_out();
        List<StoreOut> stoos = storeOutService.findStooByUidAndBid(user.getId(),book_id);
        int current_stu_book = stoos.get(0).getBook_out();
        //int current_book_count = current_tea_book + current_stu_book;
        if(isAll.equals("yes")){//退还该书的全部
            int stu_book = stoos.get(0).getBook_out()-stoos.get(0).getBook_back();
            int n = storeOutService.deleteStoreOutByUidandBid(book_id,user.getId());
            if(n>0){
                //获取对应教师的领书记录
                int current_book_count = current_tea_book + stu_book;
                storeOutService.updateTeacherBookOut(current_book_count,book_id,uid);
                return "success";
            }else {
                return "failure";
            }
        }else{
            //退还书的一部分,获取该学生的领书记录
            List<StoreOut> storeOuts = storeOutService.findStooByUidAndBid(user.getId(),book_id);
            if(storeOuts.size()>0&&storeOuts.get(0).getBook_back()!=0){
                return "cannot back";
            }else{
                int stu_book_back = Integer.parseInt(book_num);
                int current_book_count = current_tea_book + stu_book_back;
                int n = storeOutService.updateTeacherBookBack(Integer.parseInt(book_num),book_id,user.getId());
                if(n>0){
                    storeOutService.updateTeacherBookOut(current_book_count,book_id,uid);
                    return "success";
                }else {
                    return "failure";
                }
            }
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getStoreOutById")
    public String getStoreInById(@RequestParam("id")String id){
        StoreOut storeOuts = storeOutService.findStoreOutById(id);
        String book_id = storeOuts.getBook_id();
        return book_id;
    }
}
