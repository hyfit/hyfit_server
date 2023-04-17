package com.example.hyfit_server.controller.post;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.dto.Post.*;
import com.example.hyfit_server.service.image.S3Service;
import com.example.hyfit_server.service.post.PostService;
import com.example.hyfit_server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/post")
@RestController
public class PostController {

    private final PostService postService;
    private final UserService userService;

    private final S3Service s3Service;

    @PostMapping("/save")
    public BaseResponse<PostSaveResDto> savePost(HttpServletRequest request, @RequestPart(value = "file", required = false)MultipartFile file, @Valid @RequestPart(value = "dto") PostSaveDto postSaveDto, BindingResult bindingResult) throws Exception {
        try {
            String email = userService.getEmailFromToken(request);
            postSaveDto.setEmail(email);
            PostSaveResDto result = new PostSaveResDto();

            if(file == null) {
                result = postService.savePost(postSaveDto, null,bindingResult);
            } else {
                String imageUrl = s3Service.uploadFile(file, "post/images");
                result = postService.savePost(postSaveDto, imageUrl,bindingResult);
            }

            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    @GetMapping("/all")
    public BaseResponse<List<PostDto>> getAllPostsOfUser(@RequestParam String email) throws BaseException {
        try {
            List<PostDto> result = postService.getAllPostsOfUser(email);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("")
    public BaseResponse<PostDto> getOnePost(@RequestParam long id, @RequestParam String email) throws BaseException{
        try{
            PostDto postDto = postService.getOnePost(email, id);
            return new BaseResponse<>(postDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/{id}")
    public BaseResponse<PostDto> modifyPost(HttpServletRequest request, @PathVariable("id")long id,@RequestBody PostModifyDto postModifyDto) throws BaseException {
        try{
            String email = userService.getEmailFromToken(request);
            PostDto postDto = postService.modifyPost(email, id, postModifyDto);
            return new BaseResponse<>(postDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("")
    public BaseResponse<String> deletePost(HttpServletRequest request, @RequestParam long id) throws BaseException {
        try{
            String email = userService.getEmailFromToken(request);
            postService.deletePost(email, id);
            String result = "게시물 삭제 완료";
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    @GetMapping("/profile")
//    public BaseResponse<>







}
