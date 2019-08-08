package com.chatroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制页面的跳转
 */
@Controller
public class PublicController {
    /**
     * 跳转到主页即登录页面
     * @return
     */
   @RequestMapping("/")
    public String toIndex(){
        return "index";
    }

    /**
     * 条转到登录页面
     * @return
     */
    @RequestMapping("/toreg")
    public String toReg(){
        return "reg";
    }

}
