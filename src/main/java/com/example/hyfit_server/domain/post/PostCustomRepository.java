package com.example.hyfit_server.domain.post;

import com.example.hyfit_server.dto.Post.MyPostDto;
import com.example.hyfit_server.dto.Post.PostCommentListDto;
import com.example.hyfit_server.dto.Post.PostPaginationDto;
import org.springframework.data.domain.Slice;

import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PostCustomRepository {

    Slice<PostPaginationDto> searchAllFollowingBySlice(Long lastPostId, String email, List<String> followingList, Pageable pageable, String searchType);



    Slice<PostPaginationDto> searchAllBySlice(Long postId, String email, Pageable pageable, String searchType);

    List<MyPostDto> getAllMyPostListByEmail(String email);

    List<PostCommentListDto> getCommentListByPostId(Long postId);



}
