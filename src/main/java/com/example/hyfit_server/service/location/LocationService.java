package com.example.hyfit_server.service.location;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.location.LocationEntity;
import com.example.hyfit_server.domain.location.LocationRepository;
import com.example.hyfit_server.dto.location.LocationDto;
import com.example.hyfit_server.dto.location.LocationExerciseSaveReq;
import com.example.hyfit_server.dto.location.LocationPostSaveReq;
import com.example.hyfit_server.dto.location.LocationRedisReq;
import com.example.hyfit_server.dto.location.LocationRedisRes;
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

    public LocationDto saveExercise(LocationExerciseSaveReq locationReq) throws BaseException {
       LocationEntity locationEntity = locationRepository.save(locationReq.toEntity());
       return locationEntity.toDto();

    }

    public List<String> saveRedisExercise(LocationRedisReq locationReq) throws BaseException {
        String key = "exercise_" + locationReq.getId();
        String data = locationReq.getLatitude() + "," + locationReq.getLongitude() + "," + locationReq.getAltitude();
        redisService.addToList(key, data);
        // Set expiration time
//        redisService.setExpiration(key, Duration.ofDays(1));
        return redisService.getList(key,0,-1);
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
