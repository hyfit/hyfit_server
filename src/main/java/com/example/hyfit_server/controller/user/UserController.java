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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입 controller
    @PostMapping("/join")
    public BaseResponse<String> join(@Valid @RequestBody UserJoinDto userJoinDto,  BindingResult bindingResult) throws BaseException {

        // Request값 validation 처리
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError fieldEr = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                sb.append(fieldEr.getField() + " : " + message + "\n");

            });
            return new BaseResponse<>(sb.toString(), false, 400);
        }
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
    public BaseResponse<String> login(@Valid @RequestBody UserLoginDto userLoginDto, BindingResult bindingResult) throws BaseException {

        // Request값 validation 처리
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError fieldEr = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println(fieldEr.getField() + " : " + message);

                sb.append(fieldEr.getField() + " : " + message);

            });
            return new BaseResponse<>(sb.toString(), false, 400);
        }
        try{
            UserDto userDto = userService.login(userLoginDto);
            String Email = userDto.getEmail();
            UserRole role = userDto.getRole();
            long id = userDto.getUserId();
            String result = jwtTokenProvider.createToken(Email, role);
            return  new BaseResponse<>(result);
        }
        catch (BaseException exception) {
           return new BaseResponse<>((exception.getStatus()));
        }


    }

    // 회원 탈퇴 controller
    @DeleteMapping("/delete")
    public BaseResponse<String> login(HttpServletRequest request) throws BaseException {
        try{
            String token = request.getHeader("X-AUTH-TOKEN"); // header에서 토근 가져오기
            String userEmail = jwtTokenProvider.getUserPk(token); // 토근에서 이메일 정보 가져오기
            UserDto userDto = userService.delete(userEmail);
            String result = userDto.getEmail() + " 회원 탈퇴 완료";
            return  new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
