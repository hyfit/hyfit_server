package com.example.hyfit_server.service.websocket;

import com.example.hyfit_server.domain.websocket.AcceptEntity;
import com.example.hyfit_server.domain.websocket.QuitEntity;
import com.example.hyfit_server.domain.websocket.RequestEntity;
import com.example.hyfit_server.domain.websocket.WorkoutEntity;
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

    public void sendRequest(RequestEntity notificationData) {
        messagingTemplate.convertAndSend("/sub/channel/" + notificationData.getReceiver(), notificationData);
    }

    public void sendAccept(AcceptEntity notificationData) {
        messagingTemplate.convertAndSend("/sub/channel/" + notificationData.getReceiver(), notificationData);
    }

    public void sendWorkout(WorkoutEntity notificationData){
        messagingTemplate.convertAndSend("/sub/channel/" + notificationData.getReceiver(), notificationData);
    }

    public void sendQuit(QuitEntity notificationData){
        messagingTemplate.convertAndSend("/sub/channel/" + notificationData.getReceiver(), notificationData);
    }




}
