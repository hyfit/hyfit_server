package com.example.hyfit_server.dto.user;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.hyfit_server.dto.user.QUserProfileDto is a Querydsl Projection type for UserProfileDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserProfileDto extends ConstructorExpression<UserProfileDto> {

    private static final long serialVersionUID = -1020964326L;

    public QUserProfileDto(com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> nickName, com.querydsl.core.types.Expression<String> profileImgUrl) {
        super(UserProfileDto.class, new Class<?>[]{String.class, String.class, String.class}, email, nickName, profileImgUrl);
    }

}

