package com.example.hyfit_server.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<FollowEntity,Long>{
    FollowEntity findByFollowerEmailAndFollowingEmail(String followerEmail, String followingEmail);

//    FollowEntity findByFollowerEmail(String followerEmail);
//    FollowEntity findByFollowingEmail(String followingEmail);
    List<FollowEntity> findAllByFollowerEmail(String followerEmail);
    List<FollowEntity> findAllByFollowingEmail(String followingEmail);
    void deleteAllByFollowerEmail(String followerEmail);
    void deleteAllByFollowingEmail(String followingEmail);

    Long countByFollowingEmail(String followingEmail);  // 사용자의 follower 수
    Long countByFollowerEmail(String followerEmail);    // 사용자의 following 수

    void deleteByFollowerEmailAndFollowingEmail(String followerEmail, String followingEmail);
}
