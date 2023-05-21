package com.example.hyfit_server.dto.user;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserProfileDto {
    private String email;
    private String nickName;
    private String profileImgUrl;


    @QueryProjection
    public UserProfileDto(String email, String nickName,String profileImgUrl) {
        this.email = email;
        this.nickName = nickName;
        this.profileImgUrl = profileImgUrl;

    }
}
