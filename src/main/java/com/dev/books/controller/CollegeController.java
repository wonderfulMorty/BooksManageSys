package com.dev.books.controller;

import com.alibaba.fastjson.JSON;
import com.dev.books.pojo.College;
import com.dev.books.service.CollegeService;
import com.dev.books.util.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CollegeController {
    @Autowired
    CollegeService collegeService;

    @ResponseBody
    @RequestMapping(value = "/getAllCollege",produces="application/json;charset=UTF-8")
    public String getAllCollege(){
        List<College> colleges = collegeService.findAllCollege();
        Layui l = Layui.data(colleges.size(), colleges);
        return JSON.toJSONString(l);
    }
}
