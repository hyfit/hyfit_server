package com.example.hyfit_server.dto.Image;

import com.example.hyfit_server.domain.image.ImageEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImagePlaceSaveDto {

    private long placeId;

    private String imageUrl;

    public ImageEntity toEntity() {
        ImageEntity imageEntity = ImageEntity.builder()
                .placeId(placeId)
                .imageUrl(imageUrl)
                .build();
        return imageEntity;
    }

    @Builder
    public ImagePlaceSaveDto(long placeId, String imageUrl) {
        this.placeId = placeId;
        this.imageUrl = imageUrl;
    }
}
