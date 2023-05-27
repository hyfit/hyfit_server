package com.example.hyfit_server.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostCommentEntity, Long> {

    PostCommentEntity findAllByPostId(long postId);

    PostCommentEntity findByEmailAndPostIdAndCommentId(String email, long postId, long commentId);
    long countAllByPostId(long postId);

    void deleteAllByPostId(long postId);

    void deleteAllByEmail(String email);
}
