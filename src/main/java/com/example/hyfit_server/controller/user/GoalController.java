package com.example.hyfit_server.controller.user;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.config.security.JwtTokenProvider;
import com.example.hyfit_server.domain.user.GoalEntity;
import com.example.hyfit_server.domain.user.GoalRepository;
import com.example.hyfit_server.dto.Goal.GoalAddDto;
import com.example.hyfit_server.dto.Goal.GoalDto;
import com.example.hyfit_server.service.user.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.DefaultEditorKit;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/goal")
@RestController
public class GoalController {

    private final GoalService goalService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/add")
    public BaseResponse<GoalDto> addGoal(HttpServletRequest request, @RequestBody GoalAddDto goalAddDto) throws BaseException {
        try{
            String token = request.getHeader("X-AUTH-TOKEN");
            String userEmail = jwtTokenProvider.getUserPk(token);
            goalAddDto.setEmail(userEmail);
            GoalDto goalDto = goalService.addGoal(goalAddDto);
            return new BaseResponse<>(goalDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/progress")
    public BaseResponse<List<GoalDto>> getAllGoalProgress(HttpServletRequest request) throws BaseException {
        try{
            String token = request.getHeader("X-AUTH-TOKEN");
            String userEmail = jwtTokenProvider.getUserPk(token);
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
            String token = request.getHeader("X-AUTH-TOKEN");
            String userEmail = jwtTokenProvider.getUserPk(token);
            List<GoalDto> result = goalService.getAllGoalDone(userEmail);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/rate")
    public BaseResponse<GoalDto> modifyGoal(HttpServletRequest request, @RequestParam String mountain,String rate) throws BaseException {
        try{
            String token = request.getHeader("X-AUTH-TOKEN");
            String userEmail = jwtTokenProvider.getUserPk(token);
            GoalDto goalDto = goalService.modifyGoal(userEmail,mountain,rate);
            return new BaseResponse<>(goalDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("")
    public BaseResponse<String> deleteGoal(HttpServletRequest request, @RequestParam String mountain) throws BaseException {
        try{
            String token = request.getHeader("X-AUTH-TOKEN");
            String userEmail = jwtTokenProvider.getUserPk(token);
            goalService.deleteGoal(userEmail,mountain);
            String result = mountain + " 삭제완료";
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

}
