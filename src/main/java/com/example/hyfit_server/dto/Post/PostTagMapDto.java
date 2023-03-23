package com.example.hyfit_server.dto.Post;

import com.example.hyfit_server.domain.post.PostTagMapEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostTagMapDto {

    private long postTagMapId;

    private long postId;

    private long tagId;

    public PostTagMapEntity toEntity() {
        PostTagMapEntity postTagMapEntity = PostTagMapEntity.builder()
                .postTagMapId(postTagMapId)
                .postId(postId)
                .tagId(tagId)
                .build();
        return postTagMapEntity;
    }

    @Builder
    public PostTagMapDto(long postTagMapId, long postId, long tagId) {
        this.postTagMapId = postTagMapId;
        this.postId = postId;
        this.tagId = tagId;
    }
}
