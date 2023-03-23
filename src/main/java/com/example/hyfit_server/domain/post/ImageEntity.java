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
    private String fileName;

    @Builder
    public ImageEntity(long imageId, long postId, String fileName){
        this.imageId = imageId;
        this.postId = postId;
        this.fileName = fileName;
    }

    public ImageDto toDto() {
        return ImageDto.builder()
                .imageId(imageId)
                .postId(postId)
                .fileName(fileName)
                .build();
    }
}
