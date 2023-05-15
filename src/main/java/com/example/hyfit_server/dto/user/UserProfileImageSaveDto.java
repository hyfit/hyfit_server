package com.example.hyfit_server.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserProfileImageSaveDto {
    private String email;
    private String imageUrl;

    @Builder
    public UserProfileImageSaveDto(String email,String imageUrl) {
        this.email = email;
        this.imageUrl = imageUrl;
    }
}
