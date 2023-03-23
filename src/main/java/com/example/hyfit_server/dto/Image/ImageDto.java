package com.example.hyfit_server.dto.Image;

import com.example.hyfit_server.domain.post.ImageEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImageDto {

    private long imageId;

    private long postId;

    private String fileName;

    public ImageEntity toEntity() {
        ImageEntity imageEntity = ImageEntity.builder()
                .postId(postId)
                .fileName(fileName)
                .build();
        return imageEntity;
    }

    @Builder
    public ImageDto(long imageId, long postId, String fileName){
        this.imageId = imageId;
        this.postId = postId;
        this.fileName = fileName;
    }

}
