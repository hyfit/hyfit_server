package com.example.hyfit_server.dto.Post;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
public class PostPaginationDto {
    private String email;
    private String nickName;
    private String profile_img;
    private long postId;
    private String content;
    private String imageUrl;
    private String type;
    private LocalDateTime createdAt;

    @QueryProjection
    public PostPaginationDto(String email, String nickName, String profile_img, long postId, String content, String imageUrl, String type, LocalDateTime createdAt) {
        this.email = email;
        this.nickName = nickName;
        this.profile_img = profile_img;
        this.postId = postId;
        this.content = content;
        this.imageUrl = imageUrl;
        this.type = type;
        this.createdAt = createdAt;
    }

}
