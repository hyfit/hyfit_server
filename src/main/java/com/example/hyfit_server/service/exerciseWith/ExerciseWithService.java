package com.example.hyfit_server.service.exerciseWith;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.exercise.ExerciseEntity;
import com.example.hyfit_server.domain.exercise.ExerciseWithEntity;
import com.example.hyfit_server.domain.exercise.ExerciseWithRepository;
import com.example.hyfit_server.dto.exercise.ExerciseWithDto;
import com.example.hyfit_server.dto.exercise.ExerciseWithReq;
import com.example.hyfit_server.dto.exercise.ExerciseWithStart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ExerciseWithService {
    private final ExerciseWithRepository exerciseWithRepository;

    public ExerciseWithDto requestExercise(String user1Email, ExerciseWithReq exerciseWithReq)  throws BaseException {
        ExerciseWithEntity exerciseWithEntity = ExerciseWithEntity.builder()
                .user1Email(user1Email)
                .user2Email(exerciseWithReq.getUser2Email())
                .user1ExerciseId(exerciseWithReq.getUser1exerciseId())
                .build();
        return exerciseWithRepository.save(exerciseWithEntity).toDto();

    }

    public ExerciseWithDto startExerciseWith(String userEmail, ExerciseWithStart exerciseWithStart)   throws BaseException{
        ExerciseWithEntity exerciseWithEntity = exerciseWithRepository.findByExerciseWithId(exerciseWithStart.getExerciseWithId());
        // 현재 유저가 user1일때
        if(userEmail.equals(exerciseWithEntity.getUser1Email())){
            ExerciseWithEntity result = exerciseWithEntity.startExerciseWith1((int) exerciseWithStart.getExerciseId());

            return result.toDto();
        }
        else {
            ExerciseWithEntity result = exerciseWithEntity.startExerciseWith2((int) exerciseWithStart.getExerciseId());
            return result.toDto();
        }
    }

    public Integer deleteExerciseWith(int exerciseWithId)  throws BaseException{
        ExerciseWithEntity exerciseWithEntity = exerciseWithRepository.findByExerciseWithId(exerciseWithId);
        exerciseWithRepository.delete(exerciseWithEntity);
        return exerciseWithId;
    }

    public Boolean isReadyExerciseWith(int exerciseWithId)  throws BaseException{
        ExerciseWithEntity exerciseWithEntity = exerciseWithRepository.findByExerciseWithId(exerciseWithId);
        if(exerciseWithEntity.getUser1ExerciseId() != 0 &&  exerciseWithEntity.getUser2ExerciseId() != 0 ){
            return true;
        }
        else return false;
    }

}
