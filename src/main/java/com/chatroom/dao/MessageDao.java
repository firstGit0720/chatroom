package com.chatroom.dao;

import com.chatroom.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageDao {

        public boolean insterMessage(@Param("message") Message message);

}
