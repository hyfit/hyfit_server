package com.example.hyfit_server.service.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotification(String message) {
        messagingTemplate.convertAndSend("/sub/" + message, message);
    }

}
