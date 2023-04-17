package com.example.hyfit_server.service.post;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.domain.post.*;
import com.example.hyfit_server.dto.Image.ImageSaveDto;
import com.example.hyfit_server.dto.Post.*;
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

    public PostSaveResDto savePost(PostSaveDto postSaveDto, String imageUrl, Errors errors) throws BaseException {
        if(errors.hasErrors()) {
            throw new BaseException(NO_POST_CONTENTS);
        }
        PostSaveResDto result = new PostSaveResDto();
        PostEntity postEntity = postRepository.save(postSaveDto.toEntity());
        result.setPostDto(postEntity.toDto());

        if (imageUrl != null) {
            ImageSaveDto imageSaveDto = ImageSaveDto.builder()
                    .postId(postEntity.getPostId())
                    .imageUrl(imageUrl)
                    .build();

            ImageEntity imageEntity = imageRepository.save(imageSaveDto.toEntity());
            result.setImageDto(imageEntity.toDto());
        } else {
            result.setImageDto(null);
        }
        return result;
    }

    public List<PostDto> getAllPostsOfUser(String email) throws BaseException {
        List<PostDto> result = postRepository.findAllByEmail(email)
                .stream().map(m -> m.toDto())
                .collect(Collectors.toList());
        return result;
    }

    public PostDto getOnePost(String email, long id) throws BaseException {
        PostEntity postEntity = postRepository.findByEmailAndPostId(email, id);
        if(postEntity == null) {
            throw new BaseException(NO_POST_ERROR);
        }
        return postEntity.toDto();
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

        // image 삭제
        imageRepository.deleteAllByPostId(id);

        postRepository.delete(postEntity);
    }

}
