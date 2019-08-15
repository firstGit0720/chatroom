package com.chatroom.controller;

import com.chatroom.entity.User;
import com.chatroom.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 测试thymeleaf模板引擎
 *
 */
@Controller
public class TextController {

    @Autowired
    private Userservice userservice;

    @RequestMapping(value = "allUSers" , method = RequestMethod.GET)
    public String showUsers(Model model){
        List<User> users = userservice.allUsers();
        model.addAttribute("users" , users);
        return "textThylemeaf/show-user";
    }


}
