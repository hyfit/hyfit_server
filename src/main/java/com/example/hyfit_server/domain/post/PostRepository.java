package com.example.hyfit_server.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long>{

    List<PostEntity> findAllByEmail(String email);

    PostEntity findByEmailAndPostId(String email, long postId);

    PostEntity findByPostId(long id);

    void deleteAllByEmail(String email);


}
