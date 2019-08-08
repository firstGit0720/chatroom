package com.chatroom.entity;

/**
 * 好友关系表
 */
public class Relation {

    private Integer id;
    private String myId;
    private String friendId;
    private Integer status; //好友状态0：已发送了好友请求，1：成为好友，2不是好友

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
