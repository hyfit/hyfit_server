package com.example.hyfit_server.service.post;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.image.ImageEntity;
import com.example.hyfit_server.domain.image.ImageRepository;
import com.example.hyfit_server.domain.post.*;
import com.example.hyfit_server.domain.user.FollowRepository;
import com.example.hyfit_server.domain.user.UserRepository;
import com.example.hyfit_server.dto.Image.ImageDto;
import com.example.hyfit_server.dto.Image.ImagePlaceSaveDto;
import com.example.hyfit_server.dto.Image.ImagePostSaveDto;
import com.example.hyfit_server.dto.Post.*;
import com.example.hyfit_server.service.image.ImageService;
import com.example.hyfit_server.service.image.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final S3Service s3Service;
    private final ImageService imageService;

    public PostSaveRes savePost(PostSaveDto postSaveDto, String imageUrl, Errors errors) throws BaseException {
        if(errors.hasErrors()) {
            throw new BaseException(NO_POST_CONTENTS);
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

    public List<PostDto> getAllPostsOfUser(String email) throws BaseException {
        List<PostDto> result = postRepository.findAllByEmail(email)
                .stream().map(m -> m.toDto())
                .collect(Collectors.toList());
        return result;
    }

    public GetOnePostRes getOnePost(String email, long id) throws BaseException {
        PostEntity postEntity = postRepository.findByEmailAndPostId(email, id);
        if(postEntity == null) {
            throw new BaseException(NO_POST_ERROR);
        }
        String imgUrl = imageRepository.findByPostId(id).getImageUrl();
//        UserInfoMapping userInfoMapping = userRepository.findInfoByEmail(email);
//        UserProfileDto userProfileDto = UserProfileDto.builder()
//                .profileImg(userInfoMapping.getProfileImg())
//                .nickName(userInfoMapping.getNickName())
//                .build();

        GetOnePostRes result = GetOnePostRes.builder()
                .postDto(postEntity.toDto())
                .imageUrl(imgUrl)
                .userProfileDto(null)
                .build();

        return result;
    }

    public PostDto modifyPost(String email, long id, PostModifyDto postModifyDto) throws BaseException {
        PostEntity postEntity = postRepository.findByEmailAndPostId(email, id);
        if(postEntity == null) {
            throw new BaseException(NO_POST_ERROR);
        }

        postModifyDto.getContent().ifPresent(postEntity::modifyContent);

        return postEntity.toDto();
    }

    public void deletePost(String email, long id) throws BaseException {
        PostEntity postEntity = postRepository.findByEmailAndPostId(email, id);
        if(postEntity == null) {
            throw new BaseException(NO_POST_ERROR);
        }

        String imgUrl = imageRepository.findByPostId(id).getImageUrl();
        // s3 이미지 삭제
        s3Service.deleteFile(imgUrl);
        // image 삭제
        imageRepository.deleteByPostId(id);

        postRepository.delete(postEntity);
    }

//    public PostProfileRes getProfileInfo(String email) throws BaseException {
//        UserInfoMapping userInfoMapping = userRepository.findInfoByEmail(email);
//        UserProfileDto userProfileDto = UserProfileDto.builder()
//                .profileImg(userInfoMapping.getProfileImg())
//                .nickName(userInfoMapping.getNickName())
//                .build();
//
//        long postNum = postRepository.countDistinctByEmail(email);
//        long followingNum = followRepository.countByFollowerEmail(email);
//        long followerNum = followRepository.countByFollowingEmail(email);
//        PostProfileRes result = PostProfileRes.builder()
//                .email(email)
//                .userProfileDto(userProfileDto)
//                .postNum(postNum)
//                .followingNum(followingNum)
//                .followerNum(followerNum)
//                .build();
//        return result;
//    }

}
