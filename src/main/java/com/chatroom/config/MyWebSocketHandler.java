package com.chatroom.config;

import com.alibaba.fastjson.JSON;
import com.chatroom.entity.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.Session;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

/**
 * webSocket处理类
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private static final Logger logger = Logger.getLogger(MyWebSocketHandler.class);
    private static Map<Long,Set<WebSocketSession>> userSocketSessionMap = new ConcurrentHashMap<>();

    /**
     * 建立连接后
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        Long uid = (Long) webSocketSession.getAttributes().get("uid");
        logger.info("Session {} connected." + uid);

        //如果是新的就seeion ,就放入Map
        Boolean isNewUSer = true;
        for(Object o : userSocketSessionMap.entrySet()){
            Map.Entry entry = (Map.Entry) o ;
            Long key = (Long) entry.getKey();
            if (key.equals(uid)){
                userSocketSessionMap.get(uid).add(webSocketSession);
                isNewUSer = false;
                break;
            }
        }
        if (isNewUSer){
            Set<WebSocketSession> sessions = new HashSet<>();
            sessions.add(webSocketSession);
            userSocketSessionMap.put(uid,sessions);
        }
        logger.info("当前在线欧诺个户数：" + userSocketSessionMap.values().size());
    }

    /**
     * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if (webSocketMessage.getPayloadLength() == 0){
            return ;
        }

        Message msg = new Gson().fromJson(webSocketMessage.getPayload().toString(),Message.class);
        msg.setSendTime(new Date());
        //发送消息
        sendMessageToUser(Long.parseLong(msg.getReceiveId()), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
    }

    /**
     * 消息传输错误处理
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

        if(webSocketSession.isOpen()){
            webSocketSession.close();
        }
        //移除Socket回话
        for(Set<WebSocketSession> item : userSocketSessionMap.values()){
            if(item.contains(webSocketSession)){
                //删除连接Session
                item.remove(webSocketSession);
                //如果userId对应的session数为0，删除userid对应的记录
                if (0 == item.size()){
                    userSocketSessionMap.values().remove(item);
                }
            }
        }
    }
    /**
     * 关闭连接后
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.info("Session " + webSocketSession.getId() + "disconnected. Because of" + closeStatus);
        for (Set<WebSocketSession> item : userSocketSessionMap.values()) {
            if (item.contains(webSocketSession)) {
                // 删除连接 session
                item.remove(webSocketSession);
                // 如果 userId 对应的 session 数为 0 ，删除该 userId 对应的记录
                if (0 == item.size()) {
                    userSocketSessionMap.values().remove(item);
                }
                break;
            }
        }
        logger.info("当前在线用户数: "+ userSocketSessionMap.values().size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     * @throws IOException
     */
    /*public void broadcast(final TextMessage message) throws IOException {
        // 多线程群发
        for(Set<WebSocketSession> item : userSocketSessionMap.values()) {
            for (final WebSocketSession session : item) {
                if (session.isOpen()) {
                    ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                            new BasicThreadFactory.Builder().namingPattern("socket-schedule-pool-%d").daemon(true).build());
                    for (int i = 0; i < 3; i++) {
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (session.isOpen()) {
                                        logger.debug("broadcast output:" + message.toString());
                                        session.sendMessage(message);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        }
    }*/

    /**
     * 给指定用户发送消息
     * @param uid
     * @param message
     */
    public void sendMessageToUser(Long uid , TextMessage message){

        for (Long id : userSocketSessionMap.keySet()){
            if (id.equals(uid)){
                for (WebSocketSession session : userSocketSessionMap.get(uid)){
                    logger.info("SendAll : " + message);
                    try {
                        session.sendMessage(message);
                    } catch (IOException e) {
                        logger.error(e.toString());
                    }
                }
            }
        }

    }

}
