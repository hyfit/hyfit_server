package com.example.hyfit_server.controller.location;


import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.dto.location.*;
import com.example.hyfit_server.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/location")
@RestController
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/exercise")
    public BaseResponse<LocationDto> saveExercise(@RequestBody LocationExerciseSaveReq locationReq) throws BaseException {
        try{
            LocationDto locationDto = locationService.saveExercise(locationReq);
            return new BaseResponse<>(locationDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    // 고도 관련 location 저장
    @PostMapping("/redis-alt")
    public BaseResponse<String> saveRedisAltExercise(@RequestBody LocationAltRedisReq locationAltRedisReq) throws BaseException{
        try {
            String result = locationService.saveRedisAltExercise(locationAltRedisReq);
            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("/redis-exercise")
    public BaseResponse<String> saveRedisExercise(@RequestBody LocationRedisReq locationReq) throws BaseException{
        try {
            String result = locationService.saveRedisExercise(locationReq);
            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/exercise")
    public BaseResponse<List<String>> getAllExerciseList(@RequestParam int id) throws BaseException {
        try{
            List<String> result = locationService.getAllExerciseList(id);
            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/redis-exercise")
    public BaseResponse<LocationRedisRes> getRedisExercise(@RequestParam int id) throws BaseException {
        try{
            LocationRedisRes result = locationService.getMiddleLoc(id);
            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/redis-all")
    public BaseResponse<List<String>> getAllRedisExercise(@RequestParam int id) throws BaseException {
        try{
            List<String> result = locationService.getAllRedisExercise(id);
            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
