package com.example.hyfit_server.controller.user;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.config.security.JwtTokenProvider;
import com.example.hyfit_server.domain.user.GoalEntity;
import com.example.hyfit_server.domain.user.GoalRepository;
import com.example.hyfit_server.dto.Goal.GoalAddDto;
import com.example.hyfit_server.dto.Goal.GoalDto;
import com.example.hyfit_server.dto.Goal.PlaceDto;
import com.example.hyfit_server.dto.Goal.PlaceReq;
import com.example.hyfit_server.service.user.GoalService;
import com.example.hyfit_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/goal")
@RestController
public class GoalController {

    private final GoalService goalService;
    private final UserService userService;


    @PostMapping("/add")
    public BaseResponse<GoalDto> addGoal(HttpServletRequest request, @RequestBody GoalAddDto goalAddDto) throws BaseException {
        try{
            String userEmail = userService.getEmailFromToken(request);
            goalAddDto.setEmail(userEmail);
            GoalDto goalDto = goalService.addGoal(goalAddDto);
            return new BaseResponse<>(goalDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("")
    public BaseResponse<List<GoalDto>> getAllProgress(HttpServletRequest request) throws BaseException {
        try{
            String userEmail = userService.getEmailFromToken(request);
            List<GoalDto> result = goalService.getAllGoal(userEmail);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    @GetMapping("/progress")
    public BaseResponse<List<GoalDto>> getAllGoalProgress(HttpServletRequest request) throws BaseException {
        try{
            String userEmail = userService.getEmailFromToken(request);
            List<GoalDto> result = goalService.getAllGoalProgress(userEmail);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/done")
    public BaseResponse<List<GoalDto>> getAllGoalDone(HttpServletRequest request) throws BaseException {
        try{
            String userEmail = userService.getEmailFromToken(request);
            List<GoalDto> result = goalService.getAllGoalDone(userEmail);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/rate")
    public BaseResponse<GoalDto> modifyGoal(@RequestParam long id, String rate) throws BaseException {
        try{
            GoalDto goalDto = goalService.modifyGoal(id,rate);
            return new BaseResponse<>(goalDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("")
    public BaseResponse<String> deleteGoal(@RequestParam long id) throws BaseException {
        try{
            goalService.deleteGoal(id);
            String result = "삭제완료";
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    // place 대륙별로 가져오기
    @GetMapping("/place")
    public BaseResponse<List<PlaceDto>> getPlace(@RequestParam(value="type") String type, @RequestParam(value="continents") String continents)  throws BaseException {
        try{
            List<PlaceDto> result = goalService.getPlace(type, continents);
            return  new BaseResponse<>(result);
        }
        catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // place 대륙별로 가져오기 (페이지네이션)
    @GetMapping("/place-page")
    public BaseResponse<List<PlaceDto>> getPlacePage(@RequestParam(value="type") String type, @RequestParam(value="continents") String continents, @RequestParam(value="page") int page)  throws BaseException {
        try{
            List<PlaceDto> result = goalService.getPlacePage(type, continents,page);
            return  new BaseResponse<>(result);
        }
        catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // place의 페이지 개수
    @GetMapping("/place/page-size")
    public BaseResponse<Integer> getPlacePageSize(@RequestParam(value="type") String type, @RequestParam(value="continents") String continents) throws BaseException {
        try{
            List<PlaceDto> result = goalService.getPlace(type, continents);
            if( result.size() % 5 == 0){
                return  new BaseResponse<>(result.size()/5);
            }
            else return  new BaseResponse<>(result.size()/5+1);
        }
        catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
