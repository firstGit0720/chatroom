package com.chatroom.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RelationDao {

    public List<Map<String,String>> allFriends(@Param("pid") String pid);
}
