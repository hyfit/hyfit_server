package com.example.hyfit_server.dto.Post;

import com.example.hyfit_server.domain.post.PostCommentEntity;
import com.example.hyfit_server.domain.post.PostEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class SaveCommentDto {

    private long postId;

    private String content;

    private String email;

    public PostCommentEntity toEntity() {
        PostCommentEntity postCommentEntity = PostCommentEntity.builder()
                .postId(postId)
                .email(email)
                .content(content)
                .build();
        return postCommentEntity;
    }

    @Builder
    public SaveCommentDto(long postId, String content,String email){
        this.postId = postId;
        this.content = content;
        this.email = email;
    }
}
