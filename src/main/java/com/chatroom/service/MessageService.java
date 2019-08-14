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

    /**
     * 添加好友
     * @param myId
     * @param friendId
     * @return
     */
    public boolean addFriend(String myId,String friendId);

    /**
     * 查找好友请求信息
     * @param msgStatus
     * @param myId
     * @return
     */
    public List<Message> selectReqMsg( Integer msgStatus,  String myId);



}
