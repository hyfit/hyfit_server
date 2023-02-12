package com.example.hyfit_server.controller.user;


import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.config.security.JwtTokenProvider;
import com.example.hyfit_server.domain.user.UserRole;
import com.example.hyfit_server.dto.user.UserDto;
import com.example.hyfit_server.dto.user.UserJoinDto;
import com.example.hyfit_server.dto.user.UserLoginDto;
import com.example.hyfit_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입 controller
    @PostMapping("/join")
    public BaseResponse<String> join(@RequestBody UserJoinDto userJoinDto) throws BaseException {
        try{
            UserJoinDto userDto = userService.join(userJoinDto);
            String result = userDto.getEmail() + " 회원가입 성공";
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 로그인 controller
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody UserLoginDto userLoginDto) throws BaseException {
        try{
            UserDto userDto = userService.login(userLoginDto);
            String Email = userDto.getEmail();
            UserRole role = userDto.getRole();
            String result = jwtTokenProvider.createToken(Email, role);
            return  new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }


    }

    // 회원 탈퇴 controller
    @DeleteMapping("/delete")
    public BaseResponse<String> login(@RequestParam long userId) throws BaseException {
        try{
            UserDto userDto = userService.delete(userId);
            String result = userDto.getEmail() + " 회원 탈퇴 완료";
            return  new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
