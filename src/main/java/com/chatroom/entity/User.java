package com.chatroom.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Calendar;
import java.util.Date;

/**
 * 用户信息表
 */
public class User {

    private String pid;
    private String pname;
    private String username;
    @JSONField(serialize = false)
    private String password;
    private Integer sex;
    private Date birthday;
    private Integer age;
    private String phone;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        try {
            Calendar cal = Calendar.getInstance();
            int this_year = cal.get(Calendar.YEAR);
            cal.setTime(birthday);
            int born_year = cal.get(Calendar.YEAR);
            cal.set(Calendar.YEAR, this_year);
            if (cal.getTime().after(new Date())) {
                age = this_year - born_year - 1;
            } else {
                age = this_year - born_year;
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}
