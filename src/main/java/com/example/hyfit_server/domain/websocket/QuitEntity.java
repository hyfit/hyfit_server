package com.example.hyfit_server.domain.websocket;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Setter
public class QuitEntity {
    public enum Type {
        REQUEST, ACCEPT, WORKOUT, QUIT
    }
    private Type type;
    private String sender;
    private String receiver;
    public void setSender(String sender) {this.sender = sender;}
}
