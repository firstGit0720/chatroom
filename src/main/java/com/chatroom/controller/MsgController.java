package com.chatroom.controller;

import com.chatroom.entity.Message;
import com.chatroom.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 信息处理Controller
 */
@Controller
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MessageService messageService;

    /**
     * 获取最近十条的信息
     * @param sendId
     * @param receiveId
     * @return
     */
    @RequestMapping(value = "/getMessage" , method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getRecentlyMessage(@RequestParam("sendId") String sendId,@RequestParam("receiveId") String receiveId){
        return messageService.messageLists(sendId, receiveId);
    }

    /**
     * 添加好友
     * @param myId
     * @param friendId
     * @return
     */
    @RequestMapping(value = "/addFriend" , method = RequestMethod.POST)
    @ResponseBody
    public boolean addFriend(@RequestParam("myId") String myId,@RequestParam("friendId") String friendId){
        return messageService.addFriend(myId,friendId);
    }



}
