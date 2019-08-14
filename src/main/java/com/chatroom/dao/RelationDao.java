package com.chatroom.dao;

import com.chatroom.entity.Relation;
import com.chatroom.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RelationDao {

    /**
     * 获取好友信息
     * @param pid
     * @param status
     * @return
     */
    public List<Map<String,String>> allFriends(@Param("pid") String pid , @Param("status") Integer status);

    /**
     *
     * @param pid
     * @param status
     * @return
     */
    public List<User> getFriends(@Param("pid") String pid , @Param("status") Integer status);

    /**
     * 查找好友
     * @param value
     * @return
     */
    public List<Map<String,String>> selectFriend(@Param("value") String value);

    /**
     * 判断是否已经是好友
     * @param myPid
     * @param friendId
     * @return
     */
    public Relation isFriend(@Param("myPid") String myPid, @Param("friendId") String friendId);

    /**
     * 添加好友关系的信息
     * @param relation
     * @return
     */
    public boolean addFriendPrem(@Param("relaction") Relation relation);

}
