package com.example.hyfit_server.dto.Post;

import com.example.hyfit_server.domain.post.PostEntity;
import com.example.hyfit_server.domain.post.PostLikeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostLikeReq {
    private long postId;
    private String email;
    public PostLikeEntity toEntity() {
        PostLikeEntity postLikeEntity = PostLikeEntity.builder()
                .postId(postId)
                .email(email)
                .build();
        return postLikeEntity;
    }

    @Builder
    public PostLikeReq(long postId, String email){
        this.postId = postId;
        this.email = email;
    }
}
