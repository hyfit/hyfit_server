package com.example.hyfit_server.dto.Post;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@Data
public class MyPostDto {
    private long postId;
    private String postImageUrl;
    private long postLikeNum;
    private long postCommentNum;

    @Builder
    public MyPostDto(long postId, String postImageUrl, long postLikeNum, long postCommentNum) {
        this.postId = postId;
        this.postImageUrl = postImageUrl;
        this.postLikeNum = postLikeNum;
        this.postCommentNum = postCommentNum;
    }

}
