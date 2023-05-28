package com.example.hyfit_server.controller.exercise;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.domain.exercise.ExerciseEntity;
import com.example.hyfit_server.domain.exercise.ExerciseRepository;
import com.example.hyfit_server.dto.exercise.ExerciseDto;
import com.example.hyfit_server.dto.exercise.ExerciseEndReq;
import com.example.hyfit_server.dto.exercise.ExerciseStartReq;
import com.example.hyfit_server.dto.location.LocationDto;
import com.example.hyfit_server.service.exercise.ExerciseService;
import com.example.hyfit_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/exercise")
@RestController
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final UserService userService;

    private final ExerciseRepository exerciseRepository;


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

    @GetMapping("/goal")
    public BaseResponse<List<ExerciseDto>> exerciseByGoal(@RequestParam Long goalId) throws BaseException{
        try{
            List<ExerciseDto> exerciseDto = exerciseService.exerciseByGoal(goalId);
            return new BaseResponse<>(exerciseDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("")
    public  BaseResponse<ExerciseDto> getExercise(@RequestParam long exerciseId) throws BaseException{
        try{
            ExerciseDto result = exerciseService.getExercise(exerciseId);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("")
    public BaseResponse<Long> deleteExercise(@RequestParam long exerciseId) throws BaseException{
        ExerciseEntity exerciseEntity =exerciseRepository.findByExerciseId(exerciseId);
        exerciseRepository.delete(exerciseEntity);
        return new BaseResponse<>(exerciseId);
    }

    @GetMapping("/all")
    public BaseResponse<List<ExerciseDto>> getAllExercise(HttpServletRequest request) throws  BaseException{
        try{
            String email = userService.getEmailFromToken(request);
            List<ExerciseDto> result = exerciseService.getAllExercise(email);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
