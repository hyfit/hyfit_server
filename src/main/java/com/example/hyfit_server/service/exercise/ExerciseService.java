package com.example.hyfit_server.service.exercise;


import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.domain.exercise.ExerciseEntity;
import com.example.hyfit_server.domain.exercise.ExerciseRepository;
import com.example.hyfit_server.dto.exercise.ExerciseDto;
import com.example.hyfit_server.dto.exercise.ExerciseEndReq;
import com.example.hyfit_server.dto.exercise.ExerciseStartReq;
import com.example.hyfit_server.service.user.GoalService;
import com.example.hyfit_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final GoalService goalService;


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
        if(exerciseEntity.getGoalId() > 0) {
            goalService.modifyGoal(exerciseEntity.getGoalId(),exerciseEntity.getIncrease());
        }
        return exerciseEntity.toDto();
    }

    public List<ExerciseDto> exerciseByGoal(long goalId)  throws BaseException{
        List<ExerciseDto> result = exerciseRepository.findAllByGoalId(goalId).stream()
                .map(m -> m.toDto())
                .collect(Collectors.toList());
        return result;

    }

    public ExerciseDto getExercise(long exerciseId) throws BaseException{
        ExerciseDto result = exerciseRepository.findByExerciseId(exerciseId).toDto();
        return result;

    }

    public List<ExerciseDto> getAllExercise(String email) throws BaseException{
        List<ExerciseDto> result = exerciseRepository.findAllByEmail(email).stream()
                .map(m -> m.toDto())
                .collect(Collectors.toList());
        return result;
    }


}
