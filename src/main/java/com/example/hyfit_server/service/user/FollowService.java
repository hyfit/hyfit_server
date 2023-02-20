package com.example.hyfit_server.service.user;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.user.FollowEntity;
import com.example.hyfit_server.domain.user.FollowRepository;
import com.example.hyfit_server.domain.user.UserEntity;
import com.example.hyfit_server.domain.user.UserRepository;
import com.example.hyfit_server.dto.follow.FollowAddDto;
import com.example.hyfit_server.dto.follow.FollowDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.hyfit_server.config.response.BaseResponseStatus.ALREADY_FOLLOW_USER;
import static com.example.hyfit_server.config.response.BaseResponseStatus.EXIST_USER_EMAIL;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowDto addFollow (FollowAddDto followAddDto) throws BaseException {
        if(followRepository.findByFollowerEmailAndFollowingEmail(followAddDto.getFollowerEmail(), followAddDto.getFollowingEmail()) != null){ // 이미 팔로우 한 경우
            throw new BaseException(ALREADY_FOLLOW_USER);
        }
        FollowEntity followEntity = followRepository.save(followAddDto.toEntity());
        return followEntity.toDto();

    }

    public List<String> getFollower(String userEmail) throws  BaseException{
        List<String> result = followRepository.findAllByFollowingEmail(userEmail).stream()
                .map(m -> m.getFollowerEmail())
                .collect(Collectors.toList());
        return result;
    }


    public List<String> getFollowing(String userEmail) throws  BaseException{
        List<String> result = followRepository.findAllByFollowerEmail(userEmail).stream()
                .map(m -> m.getFollowingEmail())
                .collect(Collectors.toList());
        return result;

    }

    public void unFollow(String userEmail, String email) throws  BaseException {
        followRepository.deleteByFollowerEmailAndFollowingEmail(userEmail, email);
    }
}
