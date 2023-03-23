package com.example.hyfit_server.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    List<ImageEntity> findAllByPostId(long postId);

    ImageEntity findByImageId(long imageId);

    ImageEntity findByImageIdAndPostId(long imageId, long postId);

    void deleteAllByPostId(long postId);
}
