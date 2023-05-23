package com.example.hyfit_server.dto.Post;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostCommentListDto {
    private long commentId;
    private long postId;
    private String email;
    private String content;
    private String profileImg;
    private String nickName;
    private LocalDateTime createdAt;


    @QueryProjection
    public PostCommentListDto(long commentId, long postId, String email, String content, String profileImg, String nickName, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.email = email;
        this.nickName = nickName;
        this.profileImg = profileImg;
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
