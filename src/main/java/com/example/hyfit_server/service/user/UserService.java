package com.example.hyfit_server.service.user;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.config.security.JwtTokenProvider;
import com.example.hyfit_server.domain.user.FollowRepository;
import com.example.hyfit_server.domain.user.UserEntity;
import com.example.hyfit_server.domain.user.UserRepository;
import com.example.hyfit_server.dto.user.*;
import com.example.hyfit_server.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;

import static com.example.hyfit_server.config.response.BaseResponseStatus.*;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FollowRepository followRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final RedisService redisService;



    // 회원가입
    public UserJoinDto join(UserJoinDto userJoinDto) throws BaseException {
        userJoinDto.setPassword(passwordEncoder.encode(userJoinDto.getPassword()));
        if (userRepository.findByEmail(userJoinDto.getEmail()) != null) {
            //이메일 중복 처리
            throw new BaseException(EXIST_USER_EMAIL);
        }
        if(userRepository.findByNickName(userJoinDto.getNickName())!= null){
            throw new BaseException(IS_EXIST_NICKNAME);
        }
        userRepository.save(userJoinDto.toEntity()); // post 해주기
        return userJoinDto;
    }

    //로그인
    public UserDto login(UserLoginDto userLoginDto) throws BaseException{
        UserEntity userEntity = userRepository.findByEmail(userLoginDto.getEmail());
        if(userEntity == null) {
            // 해당 유저가 없는 경우
            throw new BaseException(NO_USER_ERROR);
        }
        if (!passwordEncoder.matches(userLoginDto.getPassword(), userEntity.getPassword())) {
            // 비밀번호 틀린 경우
            throw new BaseException(FAIL_TO_LOGIN);
        }
        UserDto userDto = userEntity.toDto();
        return userDto;
    }

    // 로그아웃
    public void logout(HttpServletRequest request)  throws BaseException{
        String userEmail = getEmailFromToken(request);
        String token = request.getHeader("X-AUTH-TOKEN");
        // access token 삭제
        redisService.deleteValues(userEmail);
        // refresh token 삭제
        redisService.deleteValues(token);
    }

    // user email 로 user 정보 가져오기
    public UserDto getUserInfo(String userEmail) throws BaseException{
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        return userEntity.toDto();
    }

    // 비밀번호 일치 확인
    public boolean matchPassword(String userEmail, String password) throws BaseException{
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        if(!passwordEncoder.matches(password, userEntity.getPassword())){
            throw new BaseException(FAIL_TO_LOGIN);
        }
        else return true;
    }

    // 비밀번호 수정
    public void updatePassword(String userEmail, String password, Errors errors) throws BaseException {
        if(errors.hasErrors()){
            throw new BaseException(PASSWORD_PATTERN_ERROR);
        }
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        userEntity.updatePassword(passwordEncoder.encode(password));
    }

    // 회원 정보 수정
    public UserDto update(String userEmail, UserUpdateDto userUpdateDto) throws BaseException {

        UserEntity userEntity = userRepository.findByEmail(userEmail);
        userEntity.update(userUpdateDto);
        return userEntity.toDto();
    }

    // 회원 탈퇴
    public UserDto delete(String userEmail) throws BaseException{
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        UserDto userDto = userEntity.toDto();
        if(userEntity == null) {
            // 해당 유저가 없는 경우
            throw new BaseException(NO_USER_ERROR);
        }
        // 팔로잉, 팔로워 삭제
        followRepository.deleteAllByFollowerEmail(userEmail);
        followRepository.deleteAllByFollowingEmail(userEmail);

        userRepository.delete(userEntity);

        return userDto;
    }

    // token 에서 유저 정보 가져오기
    public String getEmailFromToken(HttpServletRequest request) throws BaseException {
        String token = request.getHeader("X-AUTH-TOKEN");
        String userEmail = jwtTokenProvider.getUserPk(token);
        if(token == null) {
            throw new BaseException(NO_TOKEN_ERROR);
        }
        if(redisService.getValues(userEmail) == null){
            throw new BaseException(VALIDATE_TOKEN_ERROR);
        }
        return userEmail;
    }

    // 로그인한 유저의 유효성 검사
    public Boolean isValidUser(HttpServletRequest request) throws BaseException {
        String token = request.getHeader("X-AUTH-TOKEN");
        String userEmail = getEmailFromToken(request);
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        // 1. access token 만료된 경우
        if(redisService.getValues(userEmail) == null){
            // 1-1 access token 만료됐지만 refresh 있는경우
            if(redisService.getValues(token) != null) {
                // token 재발급
                jwtTokenProvider.reCreateToken(userEmail, userEntity.getRole());
                return true;
            }
            // 1-2 access token 만료, refresh 없음
            else {
                // 로그아웃
                return false;
            }
        }
        // 2. access token 만료 안된경우
        else {
            // 로그인 유지
            return true;
        }

    }




}
