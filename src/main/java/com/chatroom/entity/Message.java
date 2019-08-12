package com.chatroom.entity;

import java.util.Date;

/**
 * 信息表
 */
public class Message {

    private String sendId;
    private Date sendTime;
    private String receiveId;
    private String message;
    private Integer readStatus;  //阅读状态0：不在线未阅， 1 已阅,2：在线未阅

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }



    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sendId='" + sendId + '\'' +
                ", sendTime=" + sendTime +
                ", receiveId='" + receiveId + '\'' +
                ", message='" + message + '\'' +
                ", readStatus=" + readStatus +
                '}';
    }
}
