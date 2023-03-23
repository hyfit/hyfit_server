package com.example.hyfit_server.dto.Image;

import com.example.hyfit_server.domain.post.ImageEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImageSaveDto {

    private long postId;

    private String fileName;

    public ImageEntity toEntity() {
        ImageEntity imageEntity = ImageEntity.builder()
                .postId(postId)
                .fileName(fileName)
                .build();
        return imageEntity;
    }
}
