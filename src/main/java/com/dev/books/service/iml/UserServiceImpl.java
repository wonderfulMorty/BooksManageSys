package com.dev.books.service.iml;

import com.dev.books.dao.UserMapper;
import com.dev.books.pojo.User;
import com.dev.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User findOneUser(String id, String password) {
        return userMapper.findOneUser(id,password);
    }

    @Override
    public List<User> findUserByCondictions(String col_name, String prof_name, String gra_name, String ccl_name) {
        return userMapper.findUserByCondictions(col_name,prof_name,gra_name,ccl_name);
    }

    @Override
    public List<User> findAllUsersByPage(int start, int pageSize) {
        return userMapper.findAllUsersByPage(start,pageSize);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public User findTeacherByUserId(String userId) {
        return userMapper.findTeacherByUserId(userId);
    }

    @Override
    public int updateUserInfo(Map map) {
        return userMapper.updateUserInfo(map);
    }


    @Override
    public int updateUserPwd(String password, String id) {
        return userMapper.updateUserPwd(password,id);
    }

    @Override
    public User findUserById(String id) {
        return userMapper.findUserById(id);
    }

    @Override
    public int deleteUser(String id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public int deleteStuUserInfo(String user_id) {
        return userMapper.deleteStuUserInfo(user_id);
    }

    @Override
    public int deleteTeacherUserInfo(String t_id) {
        return userMapper.deleteTeacherUserInfo(t_id);
    }

    @Override
    public List<User> findAllTeacher() {
        return userMapper.findAllTeacher();
    }

    @Override
    public int insertUser(Map map){
        return userMapper.insertUser(map);
    }

    @Override
    public int insertStuUserInfo(Map map) {
        return userMapper.insertStuUserInfo(map);
    }

    @Override
    public int insertTeaUserInfo(Map map) {
        return userMapper.insertStuUserInfo(map);
    }


}
