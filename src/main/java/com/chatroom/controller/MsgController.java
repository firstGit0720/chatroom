package com.chatroom.controller;

import com.chatroom.config.MyWebSocketHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/msg")
public class MsgController {

    @Resource
    MyWebSocketHandler handler;



}
