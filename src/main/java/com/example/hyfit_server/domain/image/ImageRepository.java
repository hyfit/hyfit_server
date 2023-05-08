package com.example.hyfit_server.domain.image;

import com.example.hyfit_server.domain.image.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    ImageEntity findByImageId(long imageId);

    ImageEntity findByPostId(long postId);

    ImageEntity findByPlaceId(long placeId);

    void deleteByPostId(long postId);
    void deleteByPlaceId(long placeId);
}
