package com.example.hyfit_server.dto.Post;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.hyfit_server.dto.Post.QMyPostDto is a Querydsl Projection type for MyPostDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMyPostDto extends ConstructorExpression<MyPostDto> {

    private static final long serialVersionUID = -471730065L;

    public QMyPostDto(com.querydsl.core.types.Expression<Long> postId, com.querydsl.core.types.Expression<String> postImageUrl, com.querydsl.core.types.Expression<Long> postLikeNum, com.querydsl.core.types.Expression<Long> postCommentNum) {
        super(MyPostDto.class, new Class<?>[]{long.class, String.class, long.class, long.class}, postId, postImageUrl, postLikeNum, postCommentNum);
    }

}

