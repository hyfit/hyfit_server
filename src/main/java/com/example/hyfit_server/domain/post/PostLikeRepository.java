package com.example.hyfit_server.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long> {

    PostLikeEntity findByPostId(long postId);
    List<PostLikeEntity> findAllByPostId(long postId);
    void deleteAllByPostId(long postId);
}
