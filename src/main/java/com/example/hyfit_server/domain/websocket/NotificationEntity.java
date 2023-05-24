package com.example.hyfit_server.domain.websocket;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Setter
public class NotificationEntity {
    public enum Type {
        ENTER, ALARM, QUIT
    }
    private Type type;
    private String sender;
    private String receiver;

    private String sender_nickName;
    private String receiver_nickName;

    // exerciseWithId
    private int data;


    public void setSender(String sender) {this.sender = sender;}
    public void setData(int data){
        this.data=data;
    }
}
