package com.chatroom.service.impl;

import com.chatroom.dao.RelationDao;
import com.chatroom.entity.Relation;
import com.chatroom.entity.User;
import com.chatroom.service.RelationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RelationServiceImpl implements RelationService {

    private static final Logger logger = Logger.getLogger(RelationServiceImpl.class);

    @Autowired
    private RelationDao relationDao;



    @Override
    public List<Map<String,String>> allFriends(String pid) {
        return relationDao.allFriends(pid);
    }

    @Override
    public List<User> getFriends(String pid) {
        return relationDao.getFriends(pid);
    }

    @Override
    public List<Map<String, String>> selectFriend(String value) {
        return relationDao.selectFriend(value);
    }

    @Override
    public boolean isFriend(String myPid, String friendId) {
        logger.info("是否是朋友的判断");
        return relationDao.isFriend(myPid, friendId) == null ? true : false;
    }

    @Override
    public boolean addFriendPrem(Relation relation) {
        logger.info("添加朋友关系信息");
        return relationDao.addFriendPrem(relation);
    }

    @Override
    public boolean updateFriendStatus(Relation relation) {
        logger.info("修改朋友关系的状态");
        return relationDao.updateFriendStatus(relation);
    }


}
