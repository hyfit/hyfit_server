package com.example.hyfit_server.service.post;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponseStatus;
import com.example.hyfit_server.domain.post.ImageRepository;
import com.example.hyfit_server.domain.post.PostEntity;
import com.example.hyfit_server.domain.post.PostRepository;
import com.example.hyfit_server.domain.post.PostTagMapRepository;
import com.example.hyfit_server.dto.Post.PostDto;
import com.example.hyfit_server.dto.Post.PostModifyDto;
import com.example.hyfit_server.dto.Post.PostSaveDto;
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
    private final PostTagMapRepository postTagMapRepository;
    private final ImageRepository imageRepository;

    public PostDto savePost(PostSaveDto postSaveDto, Errors errors) throws BaseException {
        if(errors.hasErrors()) {
            throw new BaseException(NO_POST_TITLE);
        }
        PostEntity postResult = postRepository.save(postSaveDto.toEntity());
        return postResult.toDto();
    }

    public List<PostDto> getAllPosts(String email) throws BaseException {
        if(postRepository.findAllByEmail(email).size() == 0) {
            throw new BaseException(NO_SAVED_POST);
        }
        List<PostDto> result = postRepository.findAllByEmail(email)
                .stream().map(m -> m.toDto())
                .collect(Collectors.toList());
        return result;
    }

    public PostDto getOnePost(String email, long id) throws BaseException {
        PostEntity postEntity = postRepository.findByEmailAndPostId(email, id);
        return postEntity.toDto();
    }

    public PostDto modify(String email, long id, PostModifyDto postModifyDto) throws BaseException {
        PostEntity postEntity = postRepository.findByEmailAndPostId(email, id);
//        if(postModifyDto.getContent() == null) {
//            postModifyDto.setContent(postEntity.getContent());
//        }
//        postEntity.modify(postModifyDto);
        postModifyDto.getTitle().ifPresent(postEntity::modifyTitle);
        postModifyDto.getContent().ifPresent(postEntity::modifyContent);

        return postEntity.toDto();
    }

    public void deletePost(long id) throws BaseException {
        PostEntity postEntity = postRepository.findByPostId(id);

        // image 삭제
        imageRepository.deleteAllByPostId(id);

        // post_tag_map 삭제
        postTagMapRepository.deleteAllByPostId(id);

        postRepository.delete(postEntity);

    }
}
