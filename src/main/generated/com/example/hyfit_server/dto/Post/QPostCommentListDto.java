package com.example.hyfit_server.dto.Post;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.hyfit_server.dto.Post.QPostCommentListDto is a Querydsl Projection type for PostCommentListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostCommentListDto extends ConstructorExpression<PostCommentListDto> {

    private static final long serialVersionUID = -1951758682L;

    public QPostCommentListDto(com.querydsl.core.types.Expression<Long> commentId, com.querydsl.core.types.Expression<Long> postId, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> profileImg, com.querydsl.core.types.Expression<String> nickName, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt) {
        super(PostCommentListDto.class, new Class<?>[]{long.class, long.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, commentId, postId, email, content, profileImg, nickName, createdAt);
    }

}

