package com.example.hyfit_server.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    ImageEntity findByImageId(long imageId);



    void deleteAllByPostId(long postId);
}
