package com.example.hyfit_server.dto.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostProfileResDto {
    private String email;

    private String profileImage;

    private long postNum;

    private long followingNum;

    private long followerNum;
}
