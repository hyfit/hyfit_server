package com.example.hyfit_server.service.post;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.exercise.ExerciseRepository;
import com.example.hyfit_server.domain.image.ImageRepository;
import com.example.hyfit_server.domain.post.*;
import com.example.hyfit_server.domain.user.FollowRepository;
import com.example.hyfit_server.domain.user.UserRepository;
import com.example.hyfit_server.dto.Image.ImageDto;
import com.example.hyfit_server.dto.Image.ImagePostSaveDto;
import com.example.hyfit_server.dto.Post.*;
import com.example.hyfit_server.dto.user.UserProfileDto;
import com.example.hyfit_server.service.image.ImageService;
import com.example.hyfit_server.service.image.S3Service;
import com.example.hyfit_server.service.user.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.hyfit_server.config.response.BaseResponseStatus.*;
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final S3Service s3Service;
    private final ImageService imageService;
    private final PostLikeRepository postLikeRepository;
    private final ExerciseRepository exerciseRepository;
    private final FollowService followService;
    private final PostCommentRepository postCommentRepository;

    public PostSaveRes savePost(PostSaveDto postSaveDto, String imageUrl, Errors errors) throws BaseException {
        if(errors.hasErrors()) {
            throw new BaseException(NO_POST_CONTENT);
        }
        PostSaveRes result = new PostSaveRes();
        PostEntity postEntity = postRepository.save(postSaveDto.toEntity());
        result.setPostDto(postEntity.toDto());

        ImagePostSaveDto imagePostSaveDto = ImagePostSaveDto.builder()
                .postId(postEntity.getPostId())
                .imageUrl(imageUrl)
                .build();

        ImageDto imageDto = imageService.saveImage(null, imagePostSaveDto);
        result.setImageDto(imageDto);

        return result;
    }

    public List<MyPostDto> getPostListOfUser(String email) throws BaseException {
        List<MyPostDto> result = postRepository.getAllMyPostListByEmail(email);
        return result;
    }

    public Slice<PostPaginationDto> getAllPostsOfFollowingUsersWithType(String email, PostPageReq postPageReq) throws BaseException{
        List<String> followingList = followService.getFollowing(email);
        PageRequest pageRequest = PageRequest.of(0, postPageReq.getSize());
        Slice<PostPaginationDto> result = postRepository.searchAllFollowingBySlice(postPageReq.getLastPostId(), email, followingList, pageRequest, postPageReq.getSearchType());
        return result;
    }

    public Slice<PostPaginationDto> getAllPostsOfAllUsersWithType(String email, PostPageReq postPageReq) throws BaseException{
        PageRequest pageRequest = PageRequest.of(0, postPageReq.getSize());
        Slice<PostPaginationDto> result = postRepository.searchAllBySlice(postPageReq.getLastPostId(), email, pageRequest, postPageReq.getSearchType());
        return result;
    }

    public GetOnePostRes getOnePost(String email, long id) throws BaseException {
        PostEntity postEntity = postRepository.findByEmailAndPostId(email, id);
        if(postEntity == null) {
            throw new BaseException(NO_POST_ERROR);
        }
        String imgUrl = imageRepository.findByPostId(id).getImageUrl();
        UserProfileDto userProfileDto = userRepository.findInfoByEmail(email);
        long postLikeNum = postLikeRepository.countByPostId(postEntity.getPostId());
        String type = exerciseRepository.findByExerciseId(postEntity.getExercise_data_id()).getType();

        GetOnePostRes result = GetOnePostRes.builder()
                .postDto(postEntity.toDto())
                .imageUrl(imgUrl)
                .userProfileDto(userProfileDto)
                .postLikeNumber(postLikeNum)
                .type(type)
                .build();

        return result;
    }

    public PostDto modifyPost(PostModifyDto postModifyDto) throws BaseException {

        PostEntity postEntity = postRepository.findByEmailAndPostId(postModifyDto.getEmail(), postModifyDto.getPostId());
        if(postEntity == null) {
            throw new BaseException(NO_POST_ERROR);
        }

        postEntity = postEntity.modifyContent(postModifyDto.getContent());

        return postEntity.toDto();
    }

    public PostLikeDto likePost(PostLikeReq postLikeReq) throws BaseException {
        if(postLikeRepository.findByPostIdAndEmail(postLikeReq.getPostId(), postLikeReq.getEmail()) != null) {
            throw new BaseException(ALREADY_LIKED_POST);
        }
        PostLikeEntity result = postLikeRepository.save(postLikeReq.toEntity());
        return result.toDto();
    }

    public void unlikePost(PostLikeReq postLikeReq) throws BaseException {
        PostLikeEntity postLikeEntity = postLikeRepository.findByPostIdAndEmail(postLikeReq.getPostId(), postLikeReq.getEmail());
        if(postLikeEntity == null) {
            throw new BaseException(NO_POST_LIKE_ERROR);
        }
        postLikeRepository.delete(postLikeEntity);
    }

    public PostCommentDto saveComment(SaveCommentDto saveCommentDto, Errors errors) throws BaseException {
        if(errors.hasErrors()) {
            throw new BaseException(NO_COMMENT_CONTENT);
        }
        PostCommentEntity result = postCommentRepository.save(saveCommentDto.toEntity());

        return result.toDto();
    }

    public List<PostCommentListDto> getCommentList(long postId) throws BaseException {
        if(postRepository.findByPostId(postId) == null) {
            throw new BaseException(NO_POST_ERROR);
        }

        List<PostCommentListDto> result = postRepository.getCommentListByPostId(postId);
        return result;
    }


    public void deleteComment(DeleteCommentReq deleteCommentReq) throws BaseException {
        PostCommentEntity postCommentEntity = postCommentRepository.findByEmailAndPostIdAndCommentId(deleteCommentReq.getEmail(), deleteCommentReq.getPostId(),deleteCommentReq.getCommentId());
        if(postCommentEntity == null) {
            throw new BaseException(NO_COMMENT_ERROR);
        }
        postCommentRepository.delete(postCommentEntity);
    }

    public void deletePost(String email, long id) throws BaseException {
        PostEntity postEntity = postRepository.findByEmailAndPostId(email, id);
        if(postEntity == null) {
            throw new BaseException(NO_POST_ERROR);
        }

        String imgUrl = imageRepository.findByPostId(id).getImageUrl();
        // s3 이미지 파일 삭제
        s3Service.deleteFile(imgUrl);
        // image 데이터 삭제
        imageRepository.deleteByPostId(id);
        // postlike 삭제
        postLikeRepository.deleteAllByPostId(id);
        // postcomment 삭제
        postCommentRepository.deleteAllByPostId(id);

        postRepository.delete(postEntity);
    }

    public PostProfileRes getProfileInfo(String email) throws BaseException {
        UserProfileDto userProfileDto = userRepository.findInfoByEmail(email);

        long postNum = postRepository.countDistinctByEmail(email);
        long followingNum = followRepository.countByFollowerEmail(email);
        long followerNum = followRepository.countByFollowingEmail(email);
        PostProfileRes result = PostProfileRes.builder()
                .userProfileDto(userProfileDto)
                .postNum(postNum)
                .followingNum(followingNum)
                .followerNum(followerNum)
                .build();
        return result;
    }

}
