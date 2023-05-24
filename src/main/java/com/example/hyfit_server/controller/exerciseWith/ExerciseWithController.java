package com.example.hyfit_server.controller.exerciseWith;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.domain.exercise.ExerciseWithRepository;
import com.example.hyfit_server.dto.exercise.ExerciseDto;
import com.example.hyfit_server.dto.exercise.ExerciseWithDto;
import com.example.hyfit_server.dto.exercise.ExerciseWithStart;
import com.example.hyfit_server.service.exerciseWith.ExerciseWithService;
import com.example.hyfit_server.service.user.UserService;
import com.example.hyfit_server.service.websocket.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/api/exercise-with")
@RestController
public class ExerciseWithController {

    private final ExerciseWithService exerciseWithService;
    private final ExerciseWithRepository exerciseWithRepository;
    private final UserService userService;

    private final NotificationService notificationService;


    @GetMapping("")
    public BaseResponse<ExerciseWithDto> getExerciseWith(@RequestParam int exerciseWithId)throws BaseException {
        return  new BaseResponse<>(exerciseWithRepository.findByExerciseWithId(exerciseWithId).toDto());
    }

    // 처음 요청
    @PostMapping("/request")
    public BaseResponse<ExerciseWithDto> requestExercise(HttpServletRequest request, @RequestParam String user2Email) throws BaseException {
        try{
            String user1Email = userService.getEmailFromToken(request);
            ExerciseWithDto result = exerciseWithService.requestExercise(user1Email, user2Email);
            notificationService.sendNotificationForExerciseWith(user2Email, user1Email + "," + result.getExerciseWithId());
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    @PostMapping("/start")
    public  BaseResponse<ExerciseWithDto> startExerciseWith(HttpServletRequest request, @RequestBody ExerciseWithStart exerciseWithStart) throws BaseException {
        try{
            String userEmail = userService.getEmailFromToken(request);
            ExerciseWithDto result = exerciseWithService.startExerciseWith(userEmail, exerciseWithStart);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("")
    public BaseResponse<Integer> deleteExerciseWith(@RequestParam int exerciseWithId) throws BaseException {
        try{
            return new BaseResponse<>(exerciseWithService.deleteExerciseWith((exerciseWithId)));
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("is-ready")
    public BaseResponse<Boolean> isReadyExerciseWith(@RequestParam int exerciseWithId)throws BaseException {
        try{
            Boolean result = exerciseWithService.isReadyExerciseWith(exerciseWithId);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
