package com.example.hyfit_server.dto.Post;

import com.example.hyfit_server.dto.Image.ImageDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostSaveRes {
    private PostDto postDto;
    private ImageDto imageDto;

    @Builder
    public PostSaveRes(PostDto postDto, ImageDto imageDto) {
        this.postDto = postDto;
        this.imageDto = imageDto;
    }

}
