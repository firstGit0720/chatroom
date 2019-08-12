package com.chatroom.service.impl;

import com.chatroom.dao.MessageDao;
import com.chatroom.dao.RelationDao;
import com.chatroom.entity.Message;
import com.chatroom.entity.User;
import com.chatroom.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationDao relationDao;
    @Autowired
    private MessageDao messageDao;


    @Override
    public List<Map<String,String>> allFriends(String pid,Integer status) {
        return relationDao.allFriends(pid,status);
    }

    @Override
    public List<User> getFriends(String pid, Integer status) {
        return relationDao.getFriends(pid, status);
    }


    @Override
    public boolean insterMessage(Message message) {
        return messageDao.insterMessage(message);
    }
}
