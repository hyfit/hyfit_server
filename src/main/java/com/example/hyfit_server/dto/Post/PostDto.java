package com.example.hyfit_server.dto.Post;


import com.example.hyfit_server.domain.post.PostEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostDto {

    private long postId;

    private String email;

    private String type;

    private String content;


    public PostEntity toEntity() {
        PostEntity postEntity = PostEntity.builder()
                .postId(postId)
                .email(email)
                .type(type)
                .content(content)
                .build();
        return postEntity;
    }

    @Builder
    public PostDto(long postId, String email, String type, String content){
        this.postId = postId;
        this.email = email;
        this.type = type;
        this.content = content;
    }
}
