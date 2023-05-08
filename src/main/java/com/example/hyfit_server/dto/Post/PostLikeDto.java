package com.example.hyfit_server.dto.Post;

import com.example.hyfit_server.domain.post.PostLikeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostLikeDto {
    private long postLikeId;
    private long postId;
    private String email;

    public PostLikeEntity toEntity() {
        PostLikeEntity postLikeEntity = PostLikeEntity.builder()
                .postLikeId(postLikeId)
                .postId(postId)
                .email(email)
                .build();
        return postLikeEntity;
    }

    @Builder
    public PostLikeDto(long postLikeId, long postId, String email) {
        this.postLikeId = postLikeId;
        this.postId = postId;
        this.email = email;
    }

}
