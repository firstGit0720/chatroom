package com.chatroom.dao;

import com.chatroom.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao {

        /**
         * 信息的插入
         * @param message
         * @return
         */
        public boolean insterMessage(@Param("message") Message message);

        /**
         * 查询消息
         * @param sendId 发送人的id
         * @param receiveId 接收人的id
         * @param start 从几开始
         * @param end  要多少条
         * @return
         */
        public List<Message> messageLists(@Param("sendId") String sendId,@Param("receiveId") String receiveId,@Param("start") Integer start , @Param("end") Integer end);

}
