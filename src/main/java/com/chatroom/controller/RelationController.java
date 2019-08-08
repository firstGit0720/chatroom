package com.chatroom.controller;

import com.chatroom.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class RelationController {



    @RequestMapping(value = "/allfriends" , method = RequestMethod.GET)
    public String allFriends(Model model){

        return "";
    }


}
