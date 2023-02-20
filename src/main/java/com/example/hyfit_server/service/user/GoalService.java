package com.example.hyfit_server.service.user;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.user.GoalEntity;
import com.example.hyfit_server.domain.user.GoalRepository;
import com.example.hyfit_server.dto.Goal.GoalAddDto;
import com.example.hyfit_server.dto.Goal.GoalDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.hyfit_server.config.response.BaseResponseStatus.*;
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalDto addGoal(GoalAddDto goalAddDto) throws BaseException {
        GoalEntity goalEntity = goalRepository.findByMountainAndEmailAndGoalStatus(goalAddDto.getMountain(), goalAddDto.getEmail(),1);
        if(goalEntity != null){
            throw new BaseException(EXIST_GOAL_MOUNTAIN);
        }

        GoalEntity goalResult = goalRepository.save(goalAddDto.toEntity());
        return goalResult.toDto();
    }

    public List<GoalDto> getAllGoalProgress(String email) throws BaseException {
        List<GoalDto> result = goalRepository.findAllByEmailAndGoalStatus(email,1).
                stream().map(m -> m.toDto())
                .collect(Collectors.toList());
        return result;
    }

    public List<GoalDto> getAllGoalDone(String email) throws BaseException {
        List<GoalDto> result = goalRepository.findAllByEmailAndGoalStatus(email,0).
                stream().map(m -> m.toDto())
                .collect(Collectors.toList());
        return result;
    }


    public GoalDto modifyGoal(String email, String mountain, String rate) throws BaseException{
        GoalEntity goalEntity = goalRepository.findByMountainAndEmailAndGoalStatus(mountain,email,1);
        if(Long.parseLong(rate) == 100) { // 달성 완료
            goalEntity = goalEntity.modify(rate,0);
        }
        else goalEntity = goalEntity.modify(rate,1);

        goalRepository.save(goalEntity);
        return goalEntity.toDto();
    }

    public void deleteGoal(String email, String mountain) throws BaseException {
        GoalEntity goalEntity = goalRepository.findByMountainAndEmailAndGoalStatus(mountain,email,1);
        goalRepository.delete(goalEntity);
    }

}
