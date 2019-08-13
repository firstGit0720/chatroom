package com.chatroom.service;

import com.chatroom.entity.Message;

import java.util.List;

public interface MessageService {
    /**
     * 插入
     * @param message
     * @return
     */
    public boolean insterMessage(Message message);

    /**
     * 查询最近十条信息
     * @param sendId
     * @param receiveId
     * @return
     */
    public List<Message> messageLists(String sendId,String receiveId);

}
