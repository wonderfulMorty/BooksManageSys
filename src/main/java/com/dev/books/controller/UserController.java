package com.dev.books.controller;

import com.alibaba.fastjson.JSON;
import com.dev.books.pojo.Profession;
import com.dev.books.pojo.User;
import com.dev.books.service.UserService;
import com.dev.books.util.Layui;
import com.dev.books.util.RandNum;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    private final Logger log =  LoggerFactory.getLogger(UserController.class);



    @ResponseBody
    @RequestMapping(value = "/userLogin")
    @ApiOperation(value = "根据用户名获取用户对象", httpMethod = "GET", response = User.class, notes = "根据用户名获取用户对象")
    public String userLogin(String account, String password, HttpServletRequest request ){
        HttpSession session = request.getSession(true);//新建session对象
        User user = userService.findOneUser(account,password);
        session.setAttribute("user",user);
        if(user!=null){
            return "success";
        }
        return "fail";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllUser",produces="application/json;charset=UTF-8")
    public String getAllUser(@RequestParam("limit") String limit, @RequestParam("page") String page

    ){
        //System.out.println("bjshbd");
        //int error = 1/0;
        int start = (Integer.parseInt(page) - 1)*Integer.parseInt(limit);
        int pageSize = Integer.parseInt(limit);
        List<User>list = userService.findAllUsersByPage(start,pageSize);
        List<User>allData = userService.findAllUsers();
        System.out.println(list);
        Layui l = Layui.data(allData.size(), list);
        String result = JSON.toJSONString(l);
        System.out.println(result);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfo",produces="application/json;charset=UTF-8",method =RequestMethod.POST )
    public String getUserInfo(@RequestParam("limit") String limit, @RequestParam("page") String page,
                              @RequestParam("key[college_data]") String college_data,
                              @RequestParam("key[profession_data]") String profession_data,
                              @RequestParam("key[grade_data]") String grade_data,
                              @RequestParam("key[cclass_data]") String cclass_data
    ){
        System.out.println("profession_data:"+profession_data);
        List<User>list = userService.findUserByCondictions(college_data,profession_data,grade_data,cclass_data);
        Layui l = Layui.data(list.size(), list);
        return JSON.toJSONString(l);
    }

    @ResponseBody
    @RequestMapping(value = "/updateUserInfo" )
    public String updateUserInfo(@RequestBody Map map,HttpServletRequest request){
        HttpSession session = request.getSession(true);//新建session对象
        User user = (User) session.getAttribute("user");  //将对应数据存入session中
        String id = user.getId();
        map.put("id",id);
        int n = userService.updateUserInfo(map);
        if(n>0){
            return "success";
        }
        return "failure";
    }

    @ResponseBody
    @RequestMapping(value = "/updateUserPwd" )
    public String updateUserPwd(@RequestBody Map map,HttpServletRequest request){
        HttpSession session = request.getSession(true);//新建session对象
        User user = (User) session.getAttribute("user");  //将对应数据存入session中
        String id = user.getId();
        System.out.println(map);
        String password = map.get("password").toString();
        System.out.println("password:"+password);
        int n = userService.updateUserPwd(password,id);
        if(n>0){
            return "success";
        }
        return "failure";
    }

    @ResponseBody
    @RequestMapping(value = "/findUserById" ,produces="application/json;charset=UTF-8")
    public String findUserById(HttpServletRequest request){
        HttpSession session = request.getSession(true);//新建session对象
        User user = (User) session.getAttribute("user");  //将对应数据存入session中
        String id = user.getId();
        User u = userService.findUserById(id);
        List<User> list = new ArrayList<>();
        list.add(u);
        Layui l = Layui.data(list.size(), list);
        return JSON.toJSONString(l);
    }

    /*
        删除学生，教师信息
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("id")String id){
        User user = userService.findUserById(id);
        int n = 0;
        if(user.getIdentification().equals("学生")){
            userService.deleteUser(id);
            n = userService.deleteStuUserInfo(id);
            if(n>0){
               return "success";
            }else {
                return "failure";
            }
        }else if(user.getIdentification().equals("教师")){
            userService.deleteUser(id);
            userService.deleteStuUserInfo(id);
            n = userService.deleteTeacherUserInfo(id);
            if(n>0){
                return "success";
            }else {
                return "failure";
            }
        }
        return "failure";
    }

    /*
    增加学生信息
     */
    @ResponseBody
    @RequestMapping("/insertUser")
    public String insertUser(){
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/findAllTeacher",produces="application/json;charset=UTF-8")
    public String findAllTeacher(){
        List<User> users = userService.findAllTeacher();
        Layui l = Layui.data(users.size(), users);
        return JSON.toJSONString(l);
    }


    @ResponseBody
    @RequestMapping(value = "/insertStuUser",produces="application/json;charset=UTF-8")
    public String insertStuUser(@RequestBody Map map){
        Map userMap = new HashMap();
        Map userInfoMap = new HashMap();
        String id = RandNum.getGUID();
        userMap.put("id",id);
        userMap.put("identification","学生");
        userMap.put("name",map.get("name"));
        userMap.put("password",map.get("password"));
        userMap.put("phone",map.get("phone"));
        userMap.put("email",map.get("email"));
        userInfoMap.put("user_id",id);
        userInfoMap.put("col_id",map.get("col_id"));
        userInfoMap.put("prof_id",map.get("prof_id"));
        userInfoMap.put("gra_id",map.get("gra_id"));
        userInfoMap.put("ccl_id",map.get("ccl_id"));
        userInfoMap.put("t_id",map.get("t_id"));
        userService.insertUser(userMap);
        int n = userService.insertStuUserInfo(userInfoMap);
        if(n>0){
            return "success";
        }else {
            return "failure";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/insertTeaUser",produces="application/json;charset=UTF-8")
    public String insertTeaUser(@RequestBody Map map){
        Map userMap = new HashMap();
        Map teacherInfoMap = new HashMap();
        String id = RandNum.getGUID();
        userMap.put("id",id);
        userMap.put("identification","教师");
        userMap.put("name",map.get("name"));
        userMap.put("password",map.get("password"));
        userMap.put("phone",map.get("phone"));
        userMap.put("email",map.get("email"));
        teacherInfoMap.put("user_id",id);
        teacherInfoMap.put("col_id",map.get("col_id"));
        teacherInfoMap.put("prof_id",map.get("prof_id"));
        teacherInfoMap.put("gra_id",map.get("gra_id"));
        teacherInfoMap.put("ccl_id",map.get("ccl_id"));
        userService.insertUser(userMap);
        int n = userService.insertStuUserInfo(teacherInfoMap);
        if(n>0){
            return "success";
        }else {
            return "failure";
        }
    }

}
