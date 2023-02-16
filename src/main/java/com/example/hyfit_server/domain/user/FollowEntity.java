package com.example.hyfit_server.domain.user;

import com.example.hyfit_server.dto.follow.FollowDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "follow")
@Getter
@NoArgsConstructor
@Entity
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long followId;

    private String followingEmail;

    private String followerEmail;

    //회원 1이 회원 2한테 팔로잉 누르면 팔로우id 생성.
    // => follower_id  = 1 , following_id = 2
    //회원 1의 팔로잉 목록 찾을려면 followerId = 1 인 followingId 모두 가져오기 (팔로우 목록도 이런 로직)
    @Builder
    public FollowEntity(long followId, String followingEmail, String followerEmail){
        this.followId = followId;
        this.followingEmail = followingEmail;
        this.followerEmail = followerEmail;

    }

    public FollowDto toDto(){
        return FollowDto.builder()
                .followId(followId)
                .followingEmail(followingEmail)
                .followerEmail(followerEmail)
                .build();
    }


}
