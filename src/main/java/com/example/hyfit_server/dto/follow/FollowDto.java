package com.example.hyfit_server.dto.follow;

import com.example.hyfit_server.domain.user.FollowEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FollowDto {

    private long followId;

    private String followerEmail;

    private String followingEmail;

    /* DTO -> Entity */
    public FollowEntity toEntity() {
        FollowEntity followEntity = FollowEntity.builder()
                .followerEmail(followerEmail)
                .followingEmail(followingEmail)
                .build();
        return followEntity;
    }

    @Builder
    public FollowDto(long followId, String followerEmail, String followingEmail){
        this.followId = followId;
        this.followerEmail = followerEmail;
        this.followingEmail = followingEmail;
    }
}
