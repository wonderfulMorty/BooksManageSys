package com.dev.books.dao;

import com.dev.books.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    User findOneUser(String id,String password);
    List<User>findUserByCondictions(String col_name,String prof_name,String gra_name,String ccl_name );
    List<User>findAllUsersByPage(int start,int pageSize);
    List<User>findAllUsers();
    User findTeacherByUserId(String userId);
    int updateUserInfo(Map map);
    int updateUserPwd(String password,String id);
    User findUserById(String id);
    int deleteUser (String id);
    int deleteStuUserInfo(String user_id);
    int deleteTeacherUserInfo(String t_id);
    List<User> findAllTeacher();
    int insertUser(Map map);
    int insertStuUserInfo(Map map);
    int insertTeaUserInfo(Map map);
}
