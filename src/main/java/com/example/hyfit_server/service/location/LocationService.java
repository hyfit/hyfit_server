package com.example.hyfit_server.service.location;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.location.LocationEntity;
import com.example.hyfit_server.domain.location.LocationRepository;
import com.example.hyfit_server.dto.location.LocationDto;
import com.example.hyfit_server.dto.location.LocationExerciseSaveReq;
import com.example.hyfit_server.dto.location.LocationRedisReq;
import com.example.hyfit_server.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final RedisService redisService;

    public LocationDto saveExercise(LocationExerciseSaveReq locationReq) throws BaseException {
       LocationEntity locationEntity = locationRepository.save(locationReq.toEntity());
       return locationEntity.toDto();

    }

    public String saveRedisExercise(LocationRedisReq locationReq) throws BaseException {
        String key = "exercise_" + locationReq.getId();
        String data = locationReq.getLatitude() + "," + locationReq.getLongitude() + "," + locationReq.getAltitude();
        redisService.setValues(key, data, Duration.ofDays(2));
        return data;
    }
}
