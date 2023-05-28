package com.example.hyfit_server.domain.websocket;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Setter
public class WorkoutEntity {
    public enum Type {
        REQUEST, ACCEPT, WORKOUT, QUIT
    }

    private Type type;
    private String sender;
    private String receiver;

    private String locList;

//    public void setSender(String sender) {this.sender = sender;}
//    public void setData(int data){
//        this.data=data;
//    }
}
