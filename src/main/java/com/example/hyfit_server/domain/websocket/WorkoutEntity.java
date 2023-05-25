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
    private String sender;
    private String receiver;

    private List<String> locList;

//    public void setSender(String sender) {this.sender = sender;}
//    public void setData(int data){
//        this.data=data;
//    }
}
