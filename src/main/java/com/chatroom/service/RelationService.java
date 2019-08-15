package com.chatroom.service;

import com.chatroom.entity.Relation;
import com.chatroom.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 好友之间的关系
 */
public interface RelationService {

    public List<Map<String,String>> allFriends(String pid);
    public List<User> getFriends(String pid);

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

    /**
     * 添加好友关系
     * @param relation
     * @return
     */
    public boolean addFriendPrem(Relation relation);

    /**
     * 修改好友关系的状态
     * @param relation
     * @return
     */
    public boolean updateFriendStatus(Relation relation);


}
