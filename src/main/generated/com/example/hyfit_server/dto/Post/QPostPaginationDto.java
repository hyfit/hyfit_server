package com.example.hyfit_server.dto.Post;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.hyfit_server.dto.Post.QPostPaginationDto is a Querydsl Projection type for PostPaginationDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostPaginationDto extends ConstructorExpression<PostPaginationDto> {

    private static final long serialVersionUID = 76901825L;

    public QPostPaginationDto(com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> nickName, com.querydsl.core.types.Expression<String> profile_img, com.querydsl.core.types.Expression<Long> postId, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> imageUrl, com.querydsl.core.types.Expression<String> type, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt) {
        super(PostPaginationDto.class, new Class<?>[]{String.class, String.class, String.class, long.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, email, nickName, profile_img, postId, content, imageUrl, type, createdAt);
    }

}

