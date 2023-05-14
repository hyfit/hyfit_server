package com.example.hyfit_server.service.exercise;


import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.exercise.ExerciseEntity;
import com.example.hyfit_server.domain.exercise.ExerciseRepository;
import com.example.hyfit_server.dto.exercise.ExerciseDto;
import com.example.hyfit_server.dto.exercise.ExerciseEndReq;
import com.example.hyfit_server.dto.exercise.ExerciseStartReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseDto exerciseStart(String userEmail,ExerciseStartReq exerciseStartReq)  throws BaseException {
        ExerciseEntity exerciseEntity = ExerciseEntity.builder().
                email(userEmail).
                type(exerciseStartReq.getType()).
                start(LocalDateTime.parse(exerciseStartReq.getStart())).
                goalId(exerciseStartReq.getGoalId()).
                build();
        exerciseRepository.save(exerciseEntity);
        return exerciseEntity.toDto();

    }

    public ExerciseDto exerciseEnd(ExerciseEndReq exerciseEndReq) throws BaseException{
        ExerciseEntity exerciseEntity = exerciseRepository.findByExerciseId(exerciseEndReq.getExerciseId());
        exerciseEntity.exerciseEnd(exerciseEndReq);

        return exerciseEntity.toDto();

    }


}
