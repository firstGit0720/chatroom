package com.chatroom.service;


import com.chatroom.entity.User;

import java.util.List;

public interface Userservice {

    /**
     * 用户注册
     * @param user
     * @return
     */
    public boolean registerUser(User user);

    /**
     * 查询所有的会员
     * @return
     */
    public List<User> allUsers();

    /**
     * 按pid或username查询
     * @param property
     * @return
     */
    public User getUser(Object property);
    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password);

}
