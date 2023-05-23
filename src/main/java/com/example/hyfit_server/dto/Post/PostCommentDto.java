package com.example.hyfit_server.dto.Post;

import com.example.hyfit_server.domain.post.PostCommentEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostCommentDto {

    private long commentId;
    private long postId;
    private String content;
    private String email;

    public PostCommentEntity toEntity() {
        PostCommentEntity postCommentEntity = PostCommentEntity.builder()
                .commentId(commentId)
                .postId(postId)
                .content(content)
                .email(email)
                .build();
        return postCommentEntity;
    }

    @Builder
    public PostCommentDto(long commentId, long postId, String email, String content){
        this.commentId = commentId;
        this.postId = postId;
        this.content = content;
        this.email = email;
    }
}
