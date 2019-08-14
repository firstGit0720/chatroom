package com.chatroom.controller;

import com.chatroom.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class RelationController {


    @Autowired
    private RelationService relationService;

    @RequestMapping(value = "/selectfriends" , method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,String>> selectFriends(@RequestParam("value") String value , HttpServletRequest request){

        HttpSession session = request.getSession();
        //当前登录的用户的id
        String pid = session.getAttribute("pid").toString();
        //先查到所有的人
        List<Map<String,String>> selectList = relationService.selectFriend(value);
        //判断哪些以是好友哪些不是，将是还有的删除，
        for (int i = 0; i < selectList.size() ; i++){
            Map<String,String> map = selectList.get(i);
            if (map.get("pid").equals(pid)){
                selectList.remove(i);
            }
            if (!relationService.isFriend(pid,map.get("pid"))){
                selectList.remove(i);
            }
        }
        return selectList;
    }


}
