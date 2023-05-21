package com.example.hyfit_server.dto.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostModifyDto {
    private String email;
    private long postId;
    private String content;

    @Builder
    public PostModifyDto(long postId, String email, String content){
        this.postId = postId;
        this.email = email;
        this.content = content;
    }
}
