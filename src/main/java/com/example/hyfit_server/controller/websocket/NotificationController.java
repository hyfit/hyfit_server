package com.example.hyfit_server.controller.websocket;

import com.example.hyfit_server.domain.user.UserEntity;
import com.example.hyfit_server.domain.user.UserRepository;
import com.example.hyfit_server.domain.websocket.AcceptEntity;
import com.example.hyfit_server.domain.websocket.QuitEntity;
import com.example.hyfit_server.domain.websocket.RequestEntity;
import com.example.hyfit_server.domain.websocket.WorkoutEntity;
import com.example.hyfit_server.service.websocket.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    // 운동 요청 알림 보내기
    @MessageMapping("/request")
    public RequestEntity request(RequestEntity data) {
        UserEntity userEntity = userRepository.findByEmail(data.getSender());
        System.out.println("NotificationController : data() => " + data.getData());
        notificationService.sendRequest(data);
        return data;
    }

    @MessageMapping("/accept")
    public AcceptEntity accept(AcceptEntity data){
        notificationService.sendAccept(data);
        return data;
    }

    @MessageMapping("/workout")
    public WorkoutEntity workout(WorkoutEntity data){
        notificationService.sendWorkout(data);
        return data;
    }
    @MessageMapping("/quit")
    public QuitEntity quit(QuitEntity data){
        notificationService.sendQuit(data);
        return data;
    }

}
