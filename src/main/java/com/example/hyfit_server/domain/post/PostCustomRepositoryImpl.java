package com.example.hyfit_server.domain.post;
import static com.example.hyfit_server.domain.post.QPostEntity.postEntity;
import static com.example.hyfit_server.domain.user.QUserEntity.userEntity;
import static com.example.hyfit_server.domain.image.QImageEntity.imageEntity;
import static com.example.hyfit_server.domain.exercise.QExerciseEntity.exerciseEntity;
import static com.example.hyfit_server.domain.user.QFollowEntity.followEntity;

import com.example.hyfit_server.dto.Post.PostPaginationDto;
import com.example.hyfit_server.dto.Post.QPostPaginationDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostCustomRepositoryImpl implements PostCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<PostPaginationDto> searchAllFollowingBySlice(Long lastPostId, String email, List<String> followingList, Pageable pageable, String searchType) {
        List<PostPaginationDto> results =  queryFactory
                .select(new QPostPaginationDto(
                        userEntity.email,
                        userEntity.nickName,
                        userEntity.profile_img,
                        postEntity.postId,
                        postEntity.content,
                        imageEntity.imageUrl,
                        exerciseEntity.type,
                        postEntity.createdAt
                        )
                )
                .from(postEntity)
                .join(userEntity).on(postEntity.email.eq(userEntity.email))
                .join(imageEntity).on(postEntity.postId.eq(imageEntity.postId))
                .join(exerciseEntity).on(postEntity.exercise_data_id.eq(exerciseEntity.exerciseId))
                .where(ltPostId(lastPostId),
                        userEntity.email.in(followingList),
                        checkExerciseType(searchType))
                .orderBy(postEntity.postId.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return checkLastPage(pageable, results);
    }

    @Override
    public Slice<PostPaginationDto> searchAllBySlice(Long lastPostId, String email, Pageable pageable, String searchType) {
        List<PostPaginationDto> results =  queryFactory
                .select(new QPostPaginationDto(
                                userEntity.email,
                                userEntity.nickName,
                                userEntity.profile_img,
                                postEntity.postId,
                                postEntity.content,
                                imageEntity.imageUrl,
                                exerciseEntity.type,
                                postEntity.createdAt
                        )
                )
                .from(postEntity)
                .join(userEntity).on(postEntity.email.eq(userEntity.email))
                .join(imageEntity).on(postEntity.postId.eq(imageEntity.postId))
                .join(exerciseEntity).on(postEntity.exercise_data_id.eq(exerciseEntity.exerciseId))
                .where(ltPostId(lastPostId),
                        checkExerciseType(searchType))
                .orderBy(postEntity.postId.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return checkLastPage(pageable, results);
    }



    private BooleanExpression ltPostId(Long lastPostId) {
        if(lastPostId == null || lastPostId == -1) {
            return null;
        }
        return postEntity.postId.lt(lastPostId);
    }

//    private BooleanExpression checkFollowing(List<String> followingList, String email)
//    {
//        Boolean check = false;
//        Iterator<String> iterator =  followingList.iterator();
//        while(iterator.hasNext()) {
//            if(iterator.next().equals(email)) {
//                check = true;
//            }
//        }
//        if(check) {
//            return userEntity.email.in(followingList);
//        } else {
//            return null;
//        }
//    }

    private BooleanExpression checkExerciseType(String searchType) {
        if(searchType == null || searchType.isEmpty()) {
            return null;
        }
        return exerciseEntity.type.eq(searchType);
    }

    private Slice<PostPaginationDto> checkLastPage(Pageable pageable, List<PostPaginationDto> results) {
        boolean hasNext = false;

        if(results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }


}
