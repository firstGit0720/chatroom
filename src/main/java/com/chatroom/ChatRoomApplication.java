package com.chatroom;

import com.chatroom.controller.WebSocketController;
import com.chatroom.service.MessageService;
import com.chatroom.service.RelationService;
import com.chatroom.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EnableWebSocket
public class ChatRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatRoomApplication.class, args);
    }

    /**
     * 启动websocket的@ServerEndpoint
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    /**
     * 因为在@ServerEndpoint中无法正常注入service，所以通过下面的方法
     * @param relationService
     * @param messageService
     * @param userservice
     */
    @Autowired
    public void setMessageService(RelationService relationService, MessageService messageService, Userservice userservice){
        WebSocketController.relationService = relationService;
        WebSocketController.messageService = messageService;
        WebSocketController.userservice = userservice;
    }

}
