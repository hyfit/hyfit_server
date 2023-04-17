package com.example.hyfit_server.dto.Image;

import com.example.hyfit_server.domain.post.ImageEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImageSaveDto {

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
    public ImageSaveDto(long postId, String imageUrl) {
        this.postId = postId;
        this.imageUrl = imageUrl;
    }
}
