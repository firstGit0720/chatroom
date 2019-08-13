package com.chatroom.config;

import com.chatroom.controller.WebSocketController;
import com.chatroom.service.MessageService;
import com.chatroom.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket的配置类，用于注入信息和功能启动
 */
@Configuration
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //允许连接的域,只能以http或https开头
        String[] allowsOrigins = {"http://www.xxx.com"};
  /*      registry.addHandler(myHandler(), "/ws").addInterceptors(new HandShake());
        registry.addHandler(myHandler(), "/ws/sockjs").addInterceptors(new HandShake()).
        setAllowedOrigins(allowsOrigins).addInterceptors(new HandShake()).withSockJS();*/
        registry.addHandler(myHandler(), "/ws");
        registry.addHandler(myHandler(), "/ws/sockjs").withSockJS();
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyWebSocketHandler();
    }


    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Autowired
    public void setMessageService(RelationService relationService,MessageService messageService){
        WebSocketController.relationService = relationService;
        WebSocketController.messageService = messageService;
    }

}
