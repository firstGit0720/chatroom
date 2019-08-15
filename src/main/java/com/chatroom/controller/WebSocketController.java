package com.chatroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.chatroom.entity.Message;
import com.chatroom.entity.Relation;
import com.chatroom.entity.User;
import com.chatroom.service.MessageService;
import com.chatroom.service.RelationService;
import com.chatroom.service.Userservice;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义端口
 * 聊天室服务端
 * 标注为端点：@ServerEndpoint，其中"/chat-room/{username}"为访问路径
 */
@ServerEndpoint("/chat-room/{userpid}")
@Component
public class WebSocketController {
    /**
     * 存储所有存活的用户
     * 注意1：高并发问题
     * 注意2：livingSessions必须是全局变量（保证全局就他一个变量，否则每次调用都会被刷新）
     * 注意3：很难保证，用户在退出聊天室时，正确调用了WebSocket.close()，也就是调用后端的onClose()方法，这样
     *        就可能会导致Session无法被有效清除，livingSessions会越来越大，服务器压力也会越来越大。
     *        所以，我们需要周期性的去检查用户是否还处于活跃状态，不活跃的，移除该用户的session
     */
    private static Map<String , Session> livingSessions = new ConcurrentHashMap<String , Session>();  //当前在线好友
    private static Map<String,List<User>> friendsMap = new ConcurrentHashMap<String,List<User>>();  //好友人数

    public static RelationService relationService;
    public static MessageService messageService;
    public static Userservice userservice;

    /**
     * 查找他所有在线的好友
     * @param userpid
     * @return
     */
    public List<User> getFriends(String userpid){
        return relationService.getFriends(userpid);
    }
    /**
     * 前端一旦启用WebSocket,机会调用@OnOpen注解标注的方法
     * @param userpid 路径参数
     * @param session 会话，每个访问对象都会有一个单独的会话
     */
    @OnOpen
    public void openSession(@PathParam("userpid") String userpid, Session session){
        livingSessions.put(userpid, session);
        //将好友放到列表中，便于之后的消息的发送
        List<User> friendList = this.getFriends(userpid);
        friendsMap.put(userpid,friendList);
    }

    /**
     * 服务端发送消息给前端时调用
     * @param userpid   路径参数
     * @param session    会话，每个访问对象都会有一个单独的会话
     * @param message    待发送的消息
     */
    @OnMessage
    public void onMessage(@PathParam("userpid") String userpid, Session session, String message){
       //将信息添加到数据库
        Message messageStr = JSONObject.parseObject(message,Message.class);
        messageStr.setSendTime(new Date());
        //首先获取好友从list中判断
        List<User> friendList = friendsMap.get(userpid);
        //通过在线人数的session判断是否在线
        Session checkSession = livingSessions.get(messageStr.getReceiveId());
        if (checkSession == null){
            //不在线
            messageStr.setReadStatus(0);
        }else {
            messageStr.setReadStatus(2);
        }

        if(messageStr.getType()!= 1){  //普通消息
            if (messageService.insterMessage(messageStr)){
                //存入到数据库并发送至前端
                sendTextAll(messageStr.getSendId(),messageStr.getReceiveId(),JSONObject.toJSONString(messageStr));
            }
        }else{  //好友验证消息
            //获取我的信息
            User user = userservice.getUser(userpid);
            String strMsg = "用户" + user.getNickname() + "(" + userpid + "),请求添加好友，是否通过？" ;
            messageStr.setMessage(strMsg);
            if (messageService.insterMessage(messageStr)){
                Relation relation = new Relation();
                relation.setFriendId(messageStr.getReceiveId());
                relation.setMyId(userpid);
                relation.setStatus(Relation.STSTUS_CHECK);
                if(relationService.addFriendPrem(relation)){
                    //将消息发送给好友
                    //查找Session,如果在线session不为空，否则为空
                    Session session1 = livingSessions.get(messageStr.getReceiveId());
                    if(session1 != null){  //在线发送
                        sendText(session1,JSONObject.toJSONString(messageStr));
                    }
                }
            }
        }



    }

    /**
     * 客户端关闭WebSocket连接时，调用标注@OnClose的方法
     * @param userpid   路径参数
     * @param session    会话，每个访问对象都会有一个单独的会话
     */
    @OnClose
    public void onClose(@PathParam("userpid") String userpid, Session session){
        //将当前用户移除
        livingSessions.remove(userpid);
    }

    /**
     * 向指定Session(用户)发送message
     */
    private void sendText(Session session, String message){
        //发送消息对象
        RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
        try {
            //发送消息
            basicRemote.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历所有存活的用户，并发送消息（PS：就是广播消息）
     */
    private void sendTextAll(String sendId,String reciceId,String message){
        List<User> friendList = friendsMap.get(sendId);
        //给自己是必发的
        sendText(livingSessions.get(sendId), message);
//      给好友发送消息
        for(User user: friendList){
            if (user.getPid().equals(reciceId)){
                sendText(livingSessions.get(user.getPid()), message);
            }
        }
    }
}
