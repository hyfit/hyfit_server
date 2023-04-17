package com.example.hyfit_server.controller.exercise;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.dto.exercise.ExerciseDto;
import com.example.hyfit_server.dto.exercise.ExerciseEndReq;
import com.example.hyfit_server.dto.exercise.ExerciseStartReq;
import com.example.hyfit_server.dto.location.LocationDto;
import com.example.hyfit_server.service.exercise.ExerciseService;
import com.example.hyfit_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/api/exercise")
@RestController
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final UserService userService;


    @PostMapping("/start")
    public BaseResponse<ExerciseDto> exerciseStart(HttpServletRequest request,@RequestBody ExerciseStartReq exerciseStartReq) throws BaseException {
        try{
            String userEmail = userService.getEmailFromToken(request);
            ExerciseDto exerciseDto = exerciseService.exerciseStart(userEmail,exerciseStartReq);
            return new BaseResponse<>(exerciseDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("/end")
    public BaseResponse<ExerciseDto> exerciseEnd(@RequestBody ExerciseEndReq exerciseEndReq) throws BaseException{
        try{
            ExerciseDto exerciseDto = exerciseService.exerciseEnd(exerciseEndReq);
            return new BaseResponse<>(exerciseDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
