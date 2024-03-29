package com.example.hyfit_server.domain.image;

import com.example.hyfit_server.domain.user.UserEntity;
import com.example.hyfit_server.dto.Image.ImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "image")
@Getter
@NoArgsConstructor
@Entity
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

    @Column(nullable = true)
    private long postId;

    @Column(nullable = true)
    private long placeId;

    @Column(nullable = false)
    private String imageUrl;

    @Builder
    public ImageEntity(long imageId, long postId, long placeId,String imageUrl){
        this.imageId = imageId;
        this.postId = postId;
        this.placeId = placeId;
        this.imageUrl = imageUrl;
    }

    public ImageDto toDto() {
        return ImageDto.builder()
                .imageId(imageId)
                .postId(postId)
                .placeId(placeId)
                .imageUrl(imageUrl)
                .build();
    }

    public ImageEntity updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
