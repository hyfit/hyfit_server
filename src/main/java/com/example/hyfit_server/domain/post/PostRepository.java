package com.example.hyfit_server.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long>, PostCustomRepository{

    List<PostEntity> findAllByEmail(String email);

    PostEntity findByEmailAndPostId(String email, long postId);

    PostEntity findByPostId(long postId);

    Long countDistinctByEmail(String email);    // 사용자의 post 개수

    void deleteAllByEmail(String email);
}
