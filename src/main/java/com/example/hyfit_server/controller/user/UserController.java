package com.example.hyfit_server.controller.user;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.config.security.JwtTokenProvider;
import com.example.hyfit_server.domain.user.UserRole;
import com.example.hyfit_server.dto.user.*;
import com.example.hyfit_server.service.image.S3Service;
import com.example.hyfit_server.service.redis.RedisService;
import com.example.hyfit_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    private final RedisService redisService;
    private final S3Service s3Service;

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
            return  new BaseResponse<>(redisService.getValues(Email));
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 로그아웃
    @PostMapping("/logout")
    public BaseResponse<String> logout(HttpServletRequest request) throws BaseException {
        try{
            userService.logout(request);
            String result = "로그아웃 완료.";
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 유저 정보 get by token
    @GetMapping("")
    public BaseResponse<UserDto> getUserInfo(HttpServletRequest request) throws BaseException{
        try{
            String userEmail = userService.getEmailFromToken(request);
            UserDto userDto = userService.getUserInfo(userEmail);
            return new BaseResponse<>(userDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 회원 정보 수정
    @PatchMapping("")
    public BaseResponse<UserDto> update(HttpServletRequest request, @RequestBody UserUpdateDto userUpdateDto) throws BaseException {
        try{
            String userEmail = userService.getEmailFromToken(request);
            UserDto userDto = userService.update(userEmail, userUpdateDto);
            return new BaseResponse<>(userDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 비밀번호 수정 (로그인 후 수정할때)
    @PatchMapping("/password")
    public BaseResponse<String> updatePassword(HttpServletRequest request,@Valid @RequestBody UserPasswordDto userPasswordDto , BindingResult bindingResult) throws BaseException{
        try{
            String userEmail = userService.getEmailFromToken(request);
            userService.updatePassword(userEmail,userPasswordDto.getPassword(), bindingResult);
            String result = "비밀번호 변경 완료.";
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 비밀번호 수정 (비밀번호 잃어버렸을때)
    @PatchMapping("/forget-password")
    public BaseResponse<String> updatePassword(@Valid @RequestBody UserForgetPwdDto userForgetPwdDto , BindingResult bindingResult) throws BaseException{
        try{
            userService.updatePassword(userForgetPwdDto.getEmail(),userForgetPwdDto.getPassword(), bindingResult);
            String result = "비밀번호 변경 완료.";
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 비밀번호 일치 여부
    @GetMapping("/password-match")
    public BaseResponse<Boolean> matchPassword(HttpServletRequest request, @RequestParam String password) throws BaseException{
        try{
            String userEmail = userService.getEmailFromToken(request);
            boolean result = userService.matchPassword(userEmail, password);
            return new BaseResponse<>(result);
        }  catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 회원 탈퇴 controller
    @DeleteMapping("")
    public BaseResponse<String> delete(HttpServletRequest request) throws BaseException {
        try{
            String userEmail = userService.getEmailFromToken(request);
            UserDto userDto = userService.delete(userEmail);
            String result = userDto.getEmail() + " 회원 탈퇴 완료";
            return  new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    // 로그인한 유저의 유효성 검사
    @GetMapping("/valid")
    public BaseResponse<String> isValidUser(HttpServletRequest request) throws BaseException{
        try{
            String result = userService.isValidUser(request);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("/profile-image")
    public BaseResponse<UserDto> updateProfileImage(HttpServletRequest request,@RequestPart(value = "file")MultipartFile file) throws Exception {
        try {
            String email = userService.getEmailFromToken(request);
            String imageUrl = s3Service.uploadFile(file, "user/profile");

            UserProfileImageSaveDto userProfileImageSaveDto = UserProfileImageSaveDto.builder()
                    .email(email)
                    .imageUrl(imageUrl)
                    .build();

            UserDto result = userService.updateProfileImage(userProfileImageSaveDto);

            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}