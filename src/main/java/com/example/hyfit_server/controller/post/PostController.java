package com.example.hyfit_server.controller.post;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.dto.Post.PostDto;
import com.example.hyfit_server.dto.Post.PostModifyDto;
import com.example.hyfit_server.dto.Post.PostSaveDto;
import com.example.hyfit_server.service.post.PostService;
import com.example.hyfit_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/post")
@RestController
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping("/save")
    public BaseResponse<PostDto> savePost(HttpServletRequest request, @Valid @RequestBody PostSaveDto postSaveDto, BindingResult bindingResult) throws BaseException {
        try {
            String email = userService.getEmailFromToken(request);
            postSaveDto.setEmail(email);
            PostDto postDto = postService.savePost(postSaveDto, bindingResult);
            return new BaseResponse<>(postDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/all")
    public BaseResponse<List<PostDto>> getAllPosts(HttpServletRequest request) throws BaseException {
        try {
            String email = userService.getEmailFromToken(request);
            List<PostDto> result = postService.getAllPosts(email);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/{id}")
    public BaseResponse<PostDto> getOnePost(HttpServletRequest request, @PathVariable("id") long id) throws BaseException{
        try{
            String email = userService.getEmailFromToken(request);
            PostDto postDto = postService.getOnePost(email, id);
            return new BaseResponse<>(postDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/modify/{id}")
    public BaseResponse<PostDto> modify(HttpServletRequest request, @PathVariable("id")long id, @Valid @RequestBody PostModifyDto postModifyDto, BindingResult bindingResult) throws BaseException {
        try{
            String email = userService.getEmailFromToken(request);
            PostDto postDto = postService.modify(email, id, postModifyDto, bindingResult);
            return new BaseResponse<>(postDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("")
    public BaseResponse<String> deletePost(@RequestParam long id) throws BaseException {
        try{
            postService.deletePost(id);
            String result = "게시물 삭제 완료";
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
