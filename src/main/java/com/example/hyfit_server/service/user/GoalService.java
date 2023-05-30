package com.example.hyfit_server.service.user;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.domain.place.PlaceEntity;
import com.example.hyfit_server.domain.place.PlaceRepository;
import com.example.hyfit_server.domain.user.GoalEntity;
import com.example.hyfit_server.domain.user.GoalRepository;
import com.example.hyfit_server.dto.Goal.*;
import com.example.hyfit_server.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.hyfit_server.config.response.BaseResponseStatus.*;
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final PlaceRepository placeRepository;

    private final ImageService imageService;

    @Value("https://d14okywu7b1q79.cloudfront.net")
    private String cloudfrontUrl;

    public GoalDto addGoal(GoalAddDto goalAddDto) throws BaseException {
        GoalEntity goalEntity = goalRepository.findByPlaceAndEmailAndGoalStatus(goalAddDto.getPlace(), goalAddDto.getEmail(),1);
        if(goalEntity != null){
            throw new BaseException(EXIST_GOAL_MOUNTAIN);
        }

        GoalEntity goalResult = goalRepository.save(goalAddDto.toEntity());
        return goalResult.toDto();
    }
    public List<GoalDto> getAllGoal(String email) throws BaseException {
        if(goalRepository.findAllByEmail(email).size() == 0){
            throw new BaseException(NO_GOAL_EXIST);
        }
        List<GoalDto> result = goalRepository.findAllByEmail(email).
                stream().map(m -> m.toDto())
                .collect(Collectors.toList());
        return result;
    }
    public List<GoalDto> getAllGoalProgress(String email) throws BaseException {
        if(goalRepository.findAllByEmailAndGoalStatus(email,1).size() == 0){
            List<GoalDto> goalList = new ArrayList<>();
            return goalList;
        }
        List<GoalDto> result = goalRepository.findAllByEmailAndGoalStatus(email,1).
                stream().map(m -> m.toDto())
                .collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }
    // get building
    public List<GoalDto> getAllBuildingGoalProgress(String email) throws BaseException {
        if(goalRepository.findAllByEmailAndGoalStatusAndType(email,1,"building").size() == 0){
            throw new BaseException(NO_PROGRESS_GOAL);
        }
        List<GoalDto> result = goalRepository.findAllByEmailAndGoalStatusAndType(email,1,"building").
                stream().map(m -> m.toDto())
                .collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }

    // get mountain
    public List<GoalDto> getAllMountainGoalProgress(String email) throws BaseException {
        if(goalRepository.findAllByEmailAndGoalStatusAndType(email,1,"mountain").size() == 0){
            throw new BaseException(NO_PROGRESS_GOAL);
        }
        List<GoalDto> result = goalRepository.findAllByEmailAndGoalStatusAndType(email,1,"mountain").
                stream().map(m -> m.toDto())
                .collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }


    public List<GoalDto> getAllGoalDone(String email) throws BaseException {
        if(goalRepository.findAllByEmailAndGoalStatus(email,0).size() == 0){
            throw new BaseException(NO_PROGRESS_DONE);
        }
        List<GoalDto> result = goalRepository.findAllByEmailAndGoalStatus(email,0).
                stream().map(m -> m.toDto())
                .collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }


    public GoalDto modifyGoal(long id,String gain) throws BaseException{
        GoalEntity goalEntity = goalRepository.findByGoalId(id);
        PlaceEntity placeEntity = placeRepository.findByName(goalEntity.getPlace());
        Double prevGain = 0.0;
        if(goalEntity.getGain() == null || Double.parseDouble(goalEntity.getGain()) <= 0.0){
            prevGain = 0.0;
        }
        else prevGain = Double.parseDouble(goalEntity.getGain());
        Double totalGain = Double.parseDouble(gain) + prevGain;
        String totalRate = String.format("%.2f",((totalGain / Double.parseDouble(placeEntity.getAltitude())))*100);
        if(Double.parseDouble(totalRate) >= 100) { // 달성 완료
            goalEntity = goalEntity.modify(totalRate,0, String.format("%.2f",totalGain));
        }
        else goalEntity = goalEntity.modify(totalRate,1, String.format("%.2f",totalGain));
        goalEntity = goalRepository.save(goalEntity);
        return goalEntity.toDto();
    }

    public void deleteGoal(long id) throws BaseException {
        GoalEntity goalEntity = goalRepository.findByGoalId(id);
        goalRepository.delete(goalEntity);
    }

    public List<PlaceDto> getPlace(String type, String continents) throws BaseException{
        List<PlaceDto> result = placeRepository.findAllByTypeAndContinents(type, continents)
                .stream().map(m->m.toDto())
                .collect(Collectors.toList());
        return result;
    }

    public List<PlaceDto> getPlacePage(String type, String continents,int page) throws BaseException{
        int pageSize = 5;
        List<PlaceDto> result = placeRepository.findAllByTypeAndContinents(type, continents)
                .stream().map(m->m.toDto())
                .collect(Collectors.toList());
        List<PlaceDto> pageList = new ArrayList<>(result.subList((page-1)*pageSize, Math.min(page*pageSize, result.size())));
        return pageList;
    }

    public List<PlaceImageDto> getPlaceRec(String email)throws BaseException{
        List<GoalDto> goalList = getAllGoalProgress(email);
        // goalList가 빈리스트일때 (현재 진행중인 목표가 없을때)
        if(goalList.isEmpty()){
            List<PlaceImageDto> result = new ArrayList<>();
            placeRepository.findAll()
                    .forEach(entity -> {
                        PlaceImageDto dto = entity.toImageDto();
                        try{
                            dto.setSrc(cloudfrontUrl + imageService.getImageUrl(dto.getPlaceId()).getImageUrl());
                        } catch (BaseException e) {
                            throw new RuntimeException(e);
                        }
                        result.add(dto);
                    });
            return result;
        }
        else {
            List<String> nameList = goalList.stream().map(m -> m.getPlace()).collect(Collectors.toList());
            Specification<PlaceEntity> spec = (root, query, criteriaBuilder) -> {
                Predicate namePredicate = criteriaBuilder.not(root.get("name").in(nameList));
                return namePredicate;
            };

            List<PlaceImageDto> result = new ArrayList<>();

            placeRepository.findAll(spec)
                    .forEach(entity -> {
                        PlaceImageDto dto = entity.toImageDto();
                        // 요소를 추가하고 추가 작업 수행
                        try {
                            dto.setSrc(cloudfrontUrl + imageService.getImageUrl(dto.getPlaceId()).getImageUrl());
                        } catch (BaseException e) {
                            throw new RuntimeException(e);
                        }
                        result.add(dto);
                    });
            return result;
        }
    }


}
