package com.chatroom.service.impl;

import com.chatroom.dao.MessageDao;
import com.chatroom.dao.RelationDao;
import com.chatroom.dao.UserDao;
import com.chatroom.entity.Message;
import com.chatroom.entity.Relation;
import com.chatroom.entity.User;
import com.chatroom.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RelationDao relationDao;
    @Override
    public boolean insterMessage(Message message) {
        return messageDao.insterMessage(message);
    }

    @Override
    public List<Message> messageLists(String sendId, String receiveId) {
        List<Message> lists = messageDao.messageLists(sendId,receiveId,0,10);
        //倒序一下
        Collections.reverse(lists);
        logger.info("获取好友列表");
        return lists;
    }

    @Override
    @Transactional
    public boolean addFriend(String myId, String friendId) {
        //获取我的信息
        User user = userDao.getUser(myId);
        //添加好友请求信息
        String strMsg = "用户" + user.getNickname() + "(" + myId + "),请求添加好友，是否通过？" ;
        Message message = new Message();
        message.setSendId(myId);
        message.setReceiveId(friendId);
        message.setSendTime(new Date());
        message.setMessage(strMsg);
        message.setType(1);
        if(messageDao.insterMessage(message)){
            logger.info("好友请求信息添加成功，开始添加好友关系信息！");
            Relation relation = new Relation();
            relation.setFriendId(friendId);
            relation.setMyId(myId);
            relation.setStatus(Relation.STSTUS_CHECK);
            if(relationDao.addFriendPrem(relation)){
                logger.info("好友关系添加成功");
                return true;
            }else{
                logger.warn("好友关系添加失败");
            }
        }else{
            logger.warn("好友请求信息添加失败");
        }
        return false;
    }

    @Override
    public List<Message> selectReqMsg(Integer msgStatus, String myId) {
        return messageDao.selectReqMsg(msgStatus, myId);
    }


}
