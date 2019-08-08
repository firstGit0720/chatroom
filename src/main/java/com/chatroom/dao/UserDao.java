package com.chatroom.dao;

import com.chatroom.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 用户注册
     * @param user
     * @return
     */
    public boolean registerUser(@Param("user") User user);

    /**
     * 查询所有的会员
     * @return
     */
    public List<User> allUsers(@Param("username") String username,@Param("password") String password);

    /**
     * 按pid或username查询
     * @param property
     * @return
     */
    public User getUser(Object property);

}
