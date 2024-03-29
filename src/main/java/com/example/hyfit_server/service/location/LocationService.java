package com.example.hyfit_server.service.location;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.location.LocationEntity;
import com.example.hyfit_server.domain.location.LocationRepository;
import com.example.hyfit_server.dto.location.*;
import com.example.hyfit_server.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final RedisService redisService;

    // mysql 에 저장
    public LocationDto saveExercise(LocationExerciseSaveReq locationReq) throws BaseException {
       LocationEntity locationEntity = locationRepository.save(locationReq.toEntity());
       return locationEntity.toDto();

    }

    // redis에 실외운동 저장
    public String saveRedisExercise(LocationRedisReq locationReq) throws BaseException {
        String key = "exercise_" + locationReq.getId();
        String data = locationReq.getLatitude() + "," + locationReq.getLongitude() + "," + locationReq.getAltitude();
        redisService.addToList(key, data);
        return data;
    }

    // redis에 고도 운동 저장
    public String saveRedisAltExercise(LocationAltRedisReq locationAltRedisReq) throws BaseException {
        String key = "exercise_" + locationAltRedisReq.getId();
        String data = locationAltRedisReq.getLatitude() + "," + locationAltRedisReq.getLongitude() + "," + locationAltRedisReq.getAltitude() +  "," + locationAltRedisReq.getIncrease();
        redisService.addToList(key, data);
        return data;
    }


    public List<String> getAllExerciseList(long id) throws BaseException {
        String key = "exercise_" + id;
        return redisService.getList(key,0,-1);
    }

    public LocationRedisRes getMiddleLoc(long id) throws BaseException {
        String key = "exercise_" + id;
        List<String> locList = redisService.getList(key,0,-1);
        int len = locList.size();
        String firstLoc = locList.get(0);
        String middleLoc = locList.get(len / 2);
        String lastLoc = locList.get(len - 1);
        LocationRedisRes locationRedisRes = LocationRedisRes.builder().start(firstLoc).middle(middleLoc).end(lastLoc).build();
        return locationRedisRes;
    }

    public List<String> getAllRedisExercise(long id) throws BaseException {

        String key = "exercise_" + id;
        List<String> locList = redisService.getList(key, 0, -1);
        return locList;
    }


}
