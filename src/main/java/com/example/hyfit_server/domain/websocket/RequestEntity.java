package com.example.hyfit_server.domain.websocket;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Setter
public class RequestEntity {
    public enum Type {
        REQUEST, ACCEPT,WORKOUT, QUIT
    }
    private Type type;
    private String sender;
    private String receiver;

    private String sender_nickName;
    private String receiver_nickName;

    private String workoutType;

    // exerciseWithId
    private int data;

    private String lat;
    private String lon;


    public void setSender(String sender) {this.sender = sender;}
    public void setData(int data){
        this.data=data;
    }
}
