package com.example.hyfit_server.domain.user;
import static com.example.hyfit_server.domain.user.QUserEntity.userEntity;

import com.example.hyfit_server.domain.post.PostEntity;
import com.example.hyfit_server.dto.user.QUserProfileDto;
import com.example.hyfit_server.dto.user.UserProfileDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;



@RequiredArgsConstructor
@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public UserProfileDto findInfoByEmail(String email) {
        return queryFactory
                .select(new QUserProfileDto(
                    userEntity.email,
                    userEntity.nickName,
                    userEntity.profile_img
                    )
                )
                .from(userEntity)
                .where(userEntity.email.eq(email))
                .fetchOne();
    }

}
