package com.chatroom.service;

import com.chatroom.entity.Message;
import com.chatroom.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 好友之间的关系
 */
public interface RelationService {

    public List<Map<String,String>> allFriends(String pid,Integer status);
    public List<User> getFriends(String pid,Integer status);

}
