package com.example.hyfit_server.dto.Image;

import com.example.hyfit_server.domain.image.ImageEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImagePostSaveDto {

    private long postId;


    private String imageUrl;

    public ImageEntity toEntity() {
        ImageEntity imageEntity = ImageEntity.builder()
                .postId(postId)
                .imageUrl(imageUrl)
                .build();
        return imageEntity;
    }

    @Builder
    public ImagePostSaveDto(long postId,String imageUrl) {
        this.postId = postId;
        this.imageUrl = imageUrl;
    }


}
