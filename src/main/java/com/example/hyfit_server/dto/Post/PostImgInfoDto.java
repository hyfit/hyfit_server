package com.example.hyfit_server.dto.Post;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PostImgInfoDto {

    private long postId;
    private String imgUrl;

    @QueryProjection
    public PostImgInfoDto(long postId, String imgUrl) {
        this.postId = postId;
        this.imgUrl = imgUrl;
    }
}
