package com.example.hyfit_server.dto.follow;

import com.example.hyfit_server.domain.user.FollowEntity;
import com.example.hyfit_server.domain.user.UserEntity;
import com.example.hyfit_server.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FollowAddDto {

    private String followerEmail;

    private String followingEmail;

    public FollowEntity toEntity() {
        FollowEntity followEntity = FollowEntity.builder()
                .followerEmail(followerEmail)
                .followingEmail(followingEmail)
                .build();
        return followEntity;
    }

    @Builder
    public FollowAddDto(String followerEmail, String followingEmail){
        this.followerEmail = followerEmail;
        this.followingEmail = followingEmail;
    }
}
