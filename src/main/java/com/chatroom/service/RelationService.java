package com.chatroom.service;

import com.chatroom.entity.Relation;
import com.chatroom.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 好友之间的关系
 */
public interface RelationService {

    public List<Map<String,String>> allFriends(String pid,Integer status);
    public List<User> getFriends(String pid,Integer status);

    /**
     * 查找好友
     * @param value
     * @return
     */
    public List<Map<String,String>> selectFriend(String value);

    /**
     * 判断是否已经是好友
     * @param myPid
     * @param friendId
     * @return
     */
    public boolean isFriend(String myPid, String friendId);

}
