package com.chatroom.service.impl;

import com.chatroom.dao.UserDao;
import com.chatroom.entity.User;
import com.chatroom.service.Userservice;
import com.chatroom.utils.DateUtils;
import com.chatroom.utils.EncryptionUtils;
import com.chatroom.utils.ImgUtils;
import com.chatroom.utils.RandomId;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements Userservice {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    private EncryptionUtils encryptionUtils;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private RandomId randomId;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public boolean registerUser(User user) {
        //密码加密
        try {
            logger.info("密码加密");
            user.setPassword(encryptionUtils.EncoderByMd5(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            logger.info("密码加密失败" +  e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.info("密码加密失败" +  e.getMessage());
        }
        //生成随机id
        user.setPid(randomId.getRandomId());
        user.setAge(dateUtils.getAge(user.getBirthday()));
        user.setImgurl(ImgUtils.DEAFUL_IMG);
        if (user.getNickname() == null || user.getNickname().length() == 0){
            user.setNickname(user.getPname());
        }
        return userDao.registerUser(user);
    }

    @Override
    public List<User> allUsers() {
        return userDao.allUsers(null,null);
    }

    @Override
    public User getUser(Object property) {
        return userDao.getUser(property);
    }

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean login(String username, String password) {
        //加密密码
        try {
            String pwd = encryptionUtils.EncoderByMd5(password);
            List<User> lists = userDao.allUsers(username, pwd);
            if (lists.size() > 0){
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateStatus(Integer status, String username) {
        return userDao.updateStatus(status, username);
    }

    /**判断用户密码是否正确
     *newpasswd 用户输入的密码
     *oldpasswd 正确密码*/
    public boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(encryptionUtils.EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }



}
