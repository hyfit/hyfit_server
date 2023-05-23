package com.example.hyfit_server.controller.post;

import com.example.hyfit_server.config.response.BaseException;
import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.dto.Post.*;
import com.example.hyfit_server.service.image.S3Service;
import com.example.hyfit_server.service.post.PostService;
import com.example.hyfit_server.service.user.UserService;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Slice;
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
    public BaseResponse<PostSaveRes> savePost(HttpServletRequest request, @RequestPart(value = "file")MultipartFile file,
                                              @Valid @RequestPart(value = "dto") PostSaveDto postSaveDto, BindingResult bindingResult) throws Exception {
        try {
            String email = userService.getEmailFromToken(request);
            postSaveDto.setEmail(email);

            String imageUrl = s3Service.uploadFile(file, "post/images");

            PostSaveRes result = postService.savePost(postSaveDto, imageUrl,bindingResult);

            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/all-posts")
    public BaseResponse<List<MyPostDto>> getPostListOfUser(@RequestParam String email) throws BaseException {
        try {
            List<MyPostDto> result = postService.getPostListOfUser(email);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/following-posts")
    public BaseResponse<Slice<PostPaginationDto>> getAllPostsOfFollowingUsersWithType(HttpServletRequest request, @RequestBody PostPageReq postPageReq) throws BaseException {
        try {
            String email = userService.getEmailFromToken(request);

            Slice<PostPaginationDto> result = postService.getAllPostsOfFollowingUsersWithType(email, postPageReq);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/all-users-post")
    public BaseResponse<Slice<PostPaginationDto>> getAllPostsOfAllUsersWithType(HttpServletRequest request, @RequestBody PostPageReq postPageReq) throws BaseException {
        try {
            String email = userService.getEmailFromToken(request);

            Slice<PostPaginationDto> result = postService.getAllPostsOfAllUsersWithType(email, postPageReq);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/{id}")
    public BaseResponse<GetOnePostRes> getOnePost(@PathVariable("id")long id, @RequestParam String email) throws BaseException{
        try{
            GetOnePostRes result = postService.getOnePost(email, id);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/{id}/modify")
    public BaseResponse<PostDto> modifyPost(HttpServletRequest request, @PathVariable("id")long id,@RequestParam String content) throws BaseException {
        try{
            String email = userService.getEmailFromToken(request);
            PostModifyDto postModifyDto = PostModifyDto.builder()
                    .postId(id)
                    .email(email)
                    .content(content)
                    .build();

            PostDto postDto = postService.modifyPost(postModifyDto);
            return new BaseResponse<>(postDto);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("/{id}/like")
    public BaseResponse<PostLikeDto> likePost(HttpServletRequest request, @PathVariable("id")long id) throws BaseException {
        try {
            String email = userService.getEmailFromToken(request);
            PostLikeReq postLikeReq = PostLikeReq.builder()
                    .postId(id)
                    .email(email)
                    .build();

            PostLikeDto result = postService.likePost(postLikeReq);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("/{id}/unlike")
    BaseResponse<String> unlikePost(HttpServletRequest request, @PathVariable("id")long id) throws BaseException {
        try{
            String email = userService.getEmailFromToken(request);
            PostLikeReq postLikeReq = PostLikeReq.builder()
                    .postId(id)
                    .email(email)
                    .build();

            postService.unlikePost(postLikeReq);
            String result = "게시물 좋아요 취소 완료";
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping("/{id}/comment/save")
    BaseResponse<PostCommentDto> saveComment(HttpServletRequest request, @PathVariable("id")long id, @Valid @RequestBody SaveCommentReq saveCommentReq, BindingResult bindingResult) throws BaseException {
        try {
            String email = userService.getEmailFromToken(request);
            SaveCommentDto saveCommentDto = SaveCommentDto.builder()
                    .postId(id)
                    .email(email)
                    .content(saveCommentReq.getContent())
                    .build();

            PostCommentDto result = postService.saveComment(saveCommentDto, bindingResult);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping({"{id}/comment"})
    BaseResponse<List<PostCommentListDto>> getCommentList(@PathVariable("id")long id) throws BaseException {
        try {
            List<PostCommentListDto> result = postService.getCommentList(id);

            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("/{id}/comment")
    BaseResponse<String> deleteComment(HttpServletRequest request, @PathVariable("id")long id, @RequestParam long commentId) throws BaseException {
        try{
            String email = userService.getEmailFromToken(request);
            DeleteCommentReq deleteCommentReq = DeleteCommentReq.builder()
                    .email(email)
                    .postId(id)
                    .commentId(commentId)
                    .build();

            postService.deleteComment(deleteCommentReq);

            String result = "게시물 삭제 완료";
            return new BaseResponse<>(result);
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

    @GetMapping("/profile")
    public BaseResponse<PostProfileRes> getCommunityProfileInfo(@RequestParam String email) throws BaseException {
        try {
            PostProfileRes result = postService.getProfileInfo(email);
            return new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
