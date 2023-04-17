package com.example.hyfit_server.domain.post;

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

    private long postId;

    @Column(nullable = false)
    private String imageUrl;

    @Builder
    public ImageEntity(long imageId, long postId, String imageUrl){
        this.imageId = imageId;
        this.postId = postId;
        this.imageUrl = imageUrl;
    }

    public ImageDto toDto() {
        return ImageDto.builder()
                .imageId(imageId)
                .postId(postId)
                .imageUrl(imageUrl)
                .build();
    }
}
