package com.example.hyfit_server.domain.websocket;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Setter
public class AcceptEntity {
    public enum Type {
        REQUEST, ACCEPT, WORKOUT, QUIT
    }
    private Type type;
    private String sender;
    private String receiver;

    private String sender_nickName;
    private String receiver_nickName;

    private int exercise1id;
    private int exercise2id;

    private String workoutType;

    // exerciseWithId
    private int data;

    private String user1lat;
    private String user1lon;

    private String user2lat;
    private String user2lon;


    public void setSender(String sender) {this.sender = sender;}
    public void setData(int data){
        this.data=data;
    }
}
