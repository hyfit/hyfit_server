package com.example.hyfit_server.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostTagMapRepository extends JpaRepository<PostTagMapEntity, Long> {

    List<PostTagMapEntity> findAllByPostId(long postId);

    PostTagMapEntity findByPostIdAndTagId(long postId, long tagId);

    void deleteAllByPostId(long postId);
}
