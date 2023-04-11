package com.example.hyfit_server.controller.location;


import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.dto.location.LocationDto;
import com.example.hyfit_server.dto.location.LocationExerciseSaveReq;
import com.example.hyfit_server.dto.location.LocationRedisReq;
import com.example.hyfit_server.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/redis-exercise")
    public BaseResponse<String> saveRedisExercise(@RequestBody LocationRedisReq locationReq) throws BaseException{
        try {
            String result = locationService.saveRedisExercise(locationReq);
            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
