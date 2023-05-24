package com.example.hyfit_server.domain.post;
import static com.example.hyfit_server.domain.post.QPostEntity.postEntity;
import static com.example.hyfit_server.domain.user.QUserEntity.userEntity;
import static com.example.hyfit_server.domain.image.QImageEntity.imageEntity;
import static com.example.hyfit_server.domain.exercise.QExerciseEntity.exerciseEntity;
import static com.example.hyfit_server.domain.post.QPostLikeEntity.postLikeEntity;
import static com.example.hyfit_server.domain.post.QPostCommentEntity.postCommentEntity;

import com.example.hyfit_server.dto.Post.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
                        checkExerciseType(searchType),
                        postIdIsNotNull())
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
                        checkExerciseType(searchType),
                        postIdIsNotNull())
                .orderBy(postEntity.postId.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return checkLastPage(pageable, results);
    }

    @Override
    public List<PostImgInfoDto> getAllMyPostListByEmail(String email) {
        return queryFactory.select( new QPostImgInfoDto(
                postEntity.postId,
                imageEntity.imageUrl
        ))
                .from(postEntity)
                .join(imageEntity).on(postEntity.postId.eq(imageEntity.postId))
                .where(postEntity.email.eq(email))
                .orderBy(postEntity.postId.desc())
                .fetch();
    }

    @Override
    public List<PostCommentListDto> getCommentListByPostId(Long postId) {
        return queryFactory.select( new QPostCommentListDto(
                postEntity.postId,
                postCommentEntity.commentId,
                postCommentEntity.email,
                postCommentEntity.content,
                userEntity.profile_img,
                userEntity.nickName,
                postCommentEntity.createdAt
                ))
                .from(postEntity)
                .join(postCommentEntity).on(postEntity.postId.eq(postCommentEntity.postId))
                .join(userEntity).on(postCommentEntity.email.eq(userEntity.email))
                .orderBy(postCommentEntity.commentId.asc())
                .fetch();
    }


    private BooleanExpression ltPostId(Long lastPostId) {
        if(lastPostId == null || lastPostId == -1) {
            return null;
        }
        return postEntity.postId.lt(lastPostId);
    }

    // NPE 방지
    private BooleanExpression postIdIsNotNull(){
        return postEntity.postId.isNotNull();
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
