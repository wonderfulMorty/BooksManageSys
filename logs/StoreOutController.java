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

    @ResponseBody
    @RequestMapping(value = "/getStoreOutByUserId",produces="application/json;charset=UTF-8")
    public String getStoreOutByUserId(@RequestParam("limit") String limit, @RequestParam("page") String page, HttpServletRequest request) {
        int total_add = 0;
        int total_sub = 0;
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
        Map<String, List<StoreOut>> map = new HashMap<String, List<StoreOut>>();
        for (StoreOut StoreOut : storeOuts) {
            System.out.println(StoreOut);
            if (map.get(StoreOut.getBook_id()) == null) {
                List<StoreOut> data = new ArrayList<StoreOut>();
                data.add(StoreOut);
                map.put(StoreOut.getBook_id(), data);
            } else {
                //定位到和之前键相同的list，然后扩充list
                List<StoreOut> data = map.get(StoreOut.getBook_id());
                data.add(StoreOut);
            }
        }

        List<StoreOut> so = new ArrayList<>();
        System.out.println("map:"+map);
        for (Map.Entry<String, List<StoreOut>> a : map.entrySet()) {
            List<StoreOut> storeOuts2 = a.getValue();
            for(int i=0;i<storeOuts2.size();i++) {
                total_add += storeOuts2.get(i).getBook_out();
                total_sub += storeOuts2.get(i).getBook_back();
            }
            Book book = bookService.findBookById(a.getKey());
            so.add(new StoreOut(book.getBook_name(),book.getBook_price(),book.getQs_name(),total_add,total_sub,total_add-total_sub));
            total_add = 0;
            total_sub = 0;
        }
        System.out.println("list表总计："+so);
        Layui l = Layui.data(so.size(), so);
        return JSON.toJSONString(l);
    }

    @ResponseBody
    @RequestMapping(value = "/inserStutReceiveBook")
    public String inserStutReceiveBook(@RequestBody Map dataMap, HttpServletRequest request) {
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
        User u = userService.findTeacherByUserId(user.getId());
        /*
        使用fastjson把类转换成json,有两个好处，
        1.需要map类型传参数
        2.fastjson能够格式化时间类型
         */
        String storeInJson = JSON.toJSONString(storeOut);
        Map map = JSON.parseObject(storeInJson, Map.class);
        System.out.println(map);
        int n = storeOutService.addReceiveBook(map);
        if (n > 0) {
            String tid = u.getT_id();
            List<StoreOut> storeOuts = storeOutService.findStoreOutByUserId(tid);//查询教师id号
            //StoreIn storeIn = storeInService.findStoreInById(storeInId);
            int init_book_count = storeOuts.get(0).getBook_out();//原来教师库存
            int current_book_count = init_book_count-Integer.parseInt(book_out);//现在库存=原来库存-已经领走库存
            storeOutService.updateTeacherBookOut(current_book_count,book_id);
            //storeInService.updateBookCount(current_book_count,storeInId);
            return "success";
        }
        return "failure";
    }
}
