package com.dev.books.controller;

import com.alibaba.fastjson.JSON;
import com.dev.books.pojo.Grade;
import com.dev.books.pojo.Profession;
import com.dev.books.service.GradeService;
import com.dev.books.service.ProfessionService;
import com.dev.books.util.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GradeController {
    @Autowired
    GradeService gradeService;

    @ResponseBody
    @RequestMapping(value = "/getAllGrade",produces="application/json;charset=UTF-8")
    public String getAllGrade(){
        List<Grade> grades = gradeService.findAllGrade();
        Layui l = Layui.data(grades.size(), grades);
        return JSON.toJSONString(l);
    }
}
