package com.codeForLearn.live_chat_application.model;

public class ChatMessage {

    private String sender;
    private String content;
    private MessageType type;
    private String timeStamp;

    public ChatMessage() {
    }

    public ChatMessage(String sender, String content, MessageType type, String timeStamp) {
        this.sender = sender;
        this.content = content;
        this.type = type;
        this.timeStamp = timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}