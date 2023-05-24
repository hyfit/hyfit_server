package com.example.hyfit_server.controller.websocket;

import com.example.hyfit_server.domain.user.UserEntity;
import com.example.hyfit_server.domain.user.UserRepository;
import com.example.hyfit_server.domain.websocket.NotificationEntity;
import com.example.hyfit_server.service.websocket.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class NotificationController {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    // 운동 요청 알림 보내기
    @MessageMapping("/request")
    public NotificationEntity request(NotificationEntity data) {
        UserEntity userEntity = userRepository.findByEmail(data.getSender());
        System.out.println("NotificationController : data() => " + data.getData());
        notificationService.sendAlarm(data);
        return data;
    }

    @MessageMapping("/accept")
    public NotificationEntity accept(NotificationEntity data){
        notificationService.sendAlarm(data);
        return data;
    }

}
