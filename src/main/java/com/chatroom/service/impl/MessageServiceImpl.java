package com.chatroom.service.impl;

import com.chatroom.dao.MessageDao;
import com.chatroom.entity.Message;
import com.chatroom.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Override
    public boolean insterMessage(Message message) {
        return messageDao.insterMessage(message);
    }

    @Override
    public List<Message> messageLists(String sendId, String receiveId) {
        List<Message> lists = messageDao.messageLists(sendId,receiveId,0,10);
        //倒序一下
        Collections.reverse(lists);
        return lists;
    }


}
