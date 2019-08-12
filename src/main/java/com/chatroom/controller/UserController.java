package com.chatroom.controller;

import com.chatroom.entity.User;
import com.chatroom.service.RelationService;
import com.chatroom.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Userservice userservice;

    @Autowired
    private RelationService relationService;

    @RequestMapping(value = "/reg" , method = RequestMethod.POST)
    public String registerUser(User user){
        if (userservice.registerUser(user)){
            //注册成功跳转到注册成功页面，并进行想登录页面的跳转
            return "reminder/reg-success";
        }else{
            //注册失败，跳转到注册失败提示页面并进行注册页面的跳转
            return "reminder/reg-error";
        }
    }


    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password , Model model, HttpServletRequest request){
        if (userservice.login(username, password)){
            //根据username查找User
            User user = userservice.getUser(username);
            //修改在线的状态
            userservice.updateStatus(1,user.getUsername());
            request.getSession().setAttribute("uid", user.getPid());
            //获取好友列表
            List<User> friends = relationService.getFriends(user.getPid(),null);
            model.addAttribute("firends", friends);
            model.addAttribute("userpid", user.getPid());
            return "chat-index";
        }else{
            model.addAttribute("msg","用户或密码错误请重新登录！");
            return "index";
        }
    }



    /**
     * 没有该方法时，前端传递过来的时间为字符串，汇报String和Date冲突的错误
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat2, true));
    }


}
