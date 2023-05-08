package com.example.workwave.entities;

public class ChatNotification {
    private String id;
    private String senderId;
    private String senderName;
    private boolean video;

    //constructors + getters and setters


    public ChatNotification() {
    }

    public ChatNotification(String id, String senderId, String senderName) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
    }
    public ChatNotification(String id, String senderId, String senderName,boolean video) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.video=video;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "ChatNotification{" +
                "id='" + id + '\'' +
                ", senderId='" + senderId + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}