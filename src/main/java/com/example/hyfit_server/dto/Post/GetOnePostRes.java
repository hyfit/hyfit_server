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
    private long postLikeNumber;
    private String type;

    @Builder
    public GetOnePostRes(PostDto postDto, String imageUrl, UserProfileDto userProfileDto, long postLikeNumber, String type) {
        this.postDto = postDto;
        this.imageUrl = imageUrl;
        this.userProfileDto = userProfileDto;
        this.postLikeNumber = postLikeNumber;
        this.type = type;
    }
}
