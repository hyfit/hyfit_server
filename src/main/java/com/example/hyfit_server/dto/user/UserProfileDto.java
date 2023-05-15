package com.example.hyfit_server.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserProfileDto {
    private String profileImgUrl;
    private String nickName;

    @Builder
    public UserProfileDto(String profileImgUrl, String nickName) {
        this.profileImgUrl = profileImgUrl;
        this.nickName = nickName;
    }
}
