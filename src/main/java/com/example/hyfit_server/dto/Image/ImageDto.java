package com.example.hyfit_server.dto.Image;

import com.example.hyfit_server.domain.image.ImageEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImageDto {

    private long imageId;

    private long postId;

    private long placeId;

    private String imageUrl;

    public ImageEntity toEntity() {
        ImageEntity imageEntity = ImageEntity.builder()
                .postId(postId)
                .placeId(placeId)
                .imageUrl(imageUrl)
                .build();
        return imageEntity;
    }

    @Builder
    public ImageDto(long imageId, long postId, long placeId,String imageUrl){
        this.imageId = imageId;
        this.postId = postId;
        this.placeId = placeId;
        this.imageUrl = imageUrl;
    }

}
