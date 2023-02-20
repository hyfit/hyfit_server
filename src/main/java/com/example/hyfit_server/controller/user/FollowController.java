package com.example.hyfit_server.controller.user;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.config.security.JwtTokenProvider;
import com.example.hyfit_server.dto.follow.FollowAddDto;
import com.example.hyfit_server.dto.follow.FollowDto;
import com.example.hyfit_server.service.user.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/follow")
@RestController
public class FollowController {

    private final FollowService followService;
    private final JwtTokenProvider jwtTokenProvider;

    // 해당 유저를 팔로잉 해주는 유저 list (해당 유저의 팔로워)
    @GetMapping("/follower")
    public BaseResponse<Map<String,List<String>>> getFollower(HttpServletRequest request) throws BaseException{
        try{
            String token = request.getHeader("X-AUTH-TOKEN"); // header에서 토근 가져오기
            String userEmail = jwtTokenProvider.getUserPk(token); // 토근에서 이메일 정보 가져오기
            Map<String, List<String>> result = new HashMap<String, List<String>>();
            List<String> followerList = followService.getFollower(userEmail);
            result.put(userEmail,followerList);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    // 해당 유저가 팔로잉하는 유저 list (해당 유저의 팔로잉)
    @GetMapping("/following")
    public BaseResponse<Map<String,List<String>>> getFollowing(HttpServletRequest request) throws BaseException{
        try{
            String token = request.getHeader("X-AUTH-TOKEN"); // header에서 토근 가져오기
            String userEmail = jwtTokenProvider.getUserPk(token); // 토근에서 이메일 정보 가져오기
            Map<String, List<String>> result = new HashMap<String, List<String>>();
            List<String> followingList = followService.getFollowing(userEmail);
            result.put(userEmail, followingList);
            System.out.println(result.getClass().getName());
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }


    }

    @PostMapping("/add")
    public BaseResponse<String> addFollow(HttpServletRequest request, @RequestParam String email) throws BaseException {
        try {
            // request에서 follow를 걸 (누군가에게 친구추가를 하는 사람) 유저의 id 가져오기
            // requestParams userId 는 팔로우 요청을 받을 유저
            String token = request.getHeader("X-AUTH-TOKEN"); // header에서 토근 가져오기
            String followerEmail = jwtTokenProvider.getUserPk(token); // 토근에서 이메일 정보 가져오기
            FollowAddDto followAddDto = FollowAddDto.builder()
                    .followerEmail(followerEmail)
                    .followingEmail(email)
                    .build();
            FollowDto followDto = followService.addFollow(followAddDto);
            String result = followDto.getFollowerEmail() + " 유저가 " + followDto.getFollowingEmail() + " 유저를 팔로우 했습니다.";
            return new BaseResponse<>(result);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("")
    public BaseResponse<String> unFollow(HttpServletRequest request, @RequestParam String email) throws BaseException {
        try{
            String token = request.getHeader("X-AUTH-TOKEN"); // header에서 토근 가져오기
            String userEmail = jwtTokenProvider.getUserPk(token); // 토근에서 이메일 정보 가져오기
            followService.unFollow(userEmail, email);
            String result = email + " 언팔로우 완료";
            return new BaseResponse<>(result);
        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
