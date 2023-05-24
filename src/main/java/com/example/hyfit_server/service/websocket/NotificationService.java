package com.example.hyfit_server.service.websocket;

import com.example.hyfit_server.domain.websocket.NotificationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotificationForExerciseWith(String userEmail,String message) {
        messagingTemplate.convertAndSend("/sub/exerciseWith/" + userEmail, message);
    }

    public void sendAlarm(NotificationEntity notificationData) {
        System.out.println("THISISDESTI!!!! : " + "/sub/channel/" + notificationData.getReceiver());
        messagingTemplate.convertAndSend("/sub/channel/" + notificationData.getReceiver(), notificationData);
    }

}
