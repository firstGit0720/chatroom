package com.chatroom.dao;

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

    public List<User> getFriends(@Param("pid") String pid , @Param("status") Integer status);
}
