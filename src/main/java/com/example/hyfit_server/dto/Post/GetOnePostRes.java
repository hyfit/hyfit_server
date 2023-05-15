package com.example.hyfit_server.dto.Post;

import com.example.hyfit_server.dto.user.UserProfileDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetOnePostRes {

    private PostDto postDto;
    private String imageUrl;
    private UserProfileDto userProfileDto;

    @Builder
    public GetOnePostRes(PostDto postDto, String imageUrl, UserProfileDto userProfileDto) {
        this.postDto = postDto;
        this.imageUrl = imageUrl;
        this.userProfileDto = userProfileDto;
    }
}
