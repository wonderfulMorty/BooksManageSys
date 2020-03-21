package com.dev.books.controller;

import com.alibaba.fastjson.JSON;
import com.dev.books.pojo.StoreIn;
import com.dev.books.pojo.StoreOut;
import com.dev.books.service.StoreInService;
import com.dev.books.util.Layui;
import com.dev.books.util.RandNum;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class StoreInController {

    @Autowired
    StoreInService storeInService;

    @ResponseBody
    @RequestMapping(value = "/getAllStoreInByPage",produces="application/json;charset=UTF-8")
    public String getAllStoreInByPage(@RequestParam("limit") String limit, @RequestParam("page") String page){
        int start = (Integer.parseInt(page) - 1)*Integer.parseInt(limit);
        int pageSize = Integer.parseInt(limit);
        List<StoreIn> storeIns = storeInService.findAllStoreInByPage(start,pageSize);
        List<StoreIn> allData = storeInService.findAllStoreIn();
        Layui l = Layui.data(allData.size(), storeIns);
        String result = JSON.toJSONString(l);
        System.out.println("result:"+result);
        return  result;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllStoreInBookName",produces="application/json;charset=UTF-8")
    public String getAllStoreInBookName(){
        List<StoreIn> allData = storeInService.findAllStoreIn();
        List<String> list = new ArrayList<>();
        for (int i=0;i<allData.size();i++){
            list.add(allData.get(i).getBook_name());
        }
        Layui l = Layui.data(allData.size(), list);
        String result = JSON.toJSONString(l);
        System.out.println("result:"+result);
        return  result;
    }

    @ResponseBody
    @RequestMapping(value = "/insertStoreIn")
    public String insertStoreIn(/*@RequestParam("store_info")String store_info
            ,@RequestParam("book_count")String book_count
            ,@RequestParam("book_name")String book_name*/
            @RequestBody Map dataMap
    ){
        System.out.println(dataMap);
        String store_info =dataMap.get("store_info").toString();
        String book_count = dataMap.get("book_count").toString();
        String book_name =dataMap.get("book_name").toString();
        String qs_name = dataMap.get("qs_name").toString();
        String id = RandNum.getGUID();
        Date date = new Date();
        StoreIn storeIn = new StoreIn(id,date,store_info,Integer.parseInt(book_count),book_name,qs_name,Integer.parseInt(book_count));
        /*
        使用fastjson把类转换成json,有两个好处，
        1.需要map类型传参数
        2.fastjson能够格式化时间类型
         */
        String storeInJson = JSON.toJSONString(storeIn);
        Map map = JSON.parseObject(storeInJson, Map.class);
        System.out.println(map);
        int n = storeInService.insertStoreIn(map);
        if(n>0){
            return "success";
        }
        return  "failure";
    }

    @ResponseBody
    @RequestMapping(value = "/getStoreInById")
    public String getStoreInById(@RequestParam("id")String id){
        StoreIn storeIn = storeInService.findStoreInById(id);
        String book_id = storeIn.getBook_id();
        return book_id;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteStoreIn")
    public String deleteStoreIn(@RequestParam("id")String id){
        int n = storeInService.deleteStoreIn(id);
        if(n>0){
            return "success";
        }
        return "failure";
    }

    @ResponseBody
    @RequestMapping(value = "/findStoreInPercent",produces="application/json;charset=UTF-8")
    public String findStoreInPercent(@RequestParam("book_type")String book_type){
        List<StoreIn> storeIns = storeInService.findStoreInPercent(book_type);
        Map<String, List<StoreIn>> map = new HashMap<String, List<StoreIn>>();
        for (StoreIn storeIn : storeIns) {
            if (map.get(storeIn.getBook_name()) == null) {
                List<StoreIn> data = new ArrayList<StoreIn>();
                data.add(storeIn);
                map.put(storeIn.getBook_name(), data);
            } else {
                //定位到和之前键相同的list，然后扩充list
                List<StoreIn> data = map.get(storeIn.getBook_name());
                data.add(storeIn);
            }
        }
        System.out.println(map);
        int book_count = 0;
        int book_init = 0;
        double persent = 0.0;
        List<StoreIn> so = new ArrayList<>();
        for (Map.Entry<String, List<StoreIn>> a : map.entrySet()) {
            List<StoreIn> sto = a.getValue();
            for(int i=0;i<sto.size();i++) {
                book_count += sto.get(i).getBook_count();
                book_init += sto.get(i).getBook_init();
                System.out.println("book_count:"+book_count);
                System.out.println("book_init:"+book_init);
                Double p = (book_init-book_count) *1.0/ book_init;
                BigDecimal bd = new BigDecimal(p);
                persent = bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                System.out.println(persent);
            }
            so.add(new StoreIn(a.getKey(),persent));
            book_count = 0;
            book_init = 0;
        }
        System.out.println("list表总计："+so);
        String jsonString = JSON.toJSONString(so);
        return jsonString;
    }
}
