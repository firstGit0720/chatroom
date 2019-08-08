package com.chatroom.service.impl;

import com.chatroom.dao.RelationDao;
import com.chatroom.entity.User;
import com.chatroom.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationDao relationDao;

    @Override
    public List<Map<String,String>> allFriends(String pid) {
        return relationDao.allFriends(pid);
    }
}
