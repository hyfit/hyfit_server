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

    private String exercise1id;
    private String exercise2id;

    private String workoutType;

    // exerciseWithId
    private int data;


    public void setSender(String sender) {this.sender = sender;}
    public void setData(int data){
        this.data=data;
    }
}
