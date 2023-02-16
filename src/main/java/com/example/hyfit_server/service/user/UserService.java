package com.example.hyfit_server.service.user;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.security.JwtTokenProvider;
import com.example.hyfit_server.domain.user.FollowRepository;
import com.example.hyfit_server.domain.user.UserEntity;
import com.example.hyfit_server.domain.user.UserRepository;
import com.example.hyfit_server.dto.user.UserDto;
import com.example.hyfit_server.dto.user.UserJoinDto;
import com.example.hyfit_server.dto.user.UserLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
