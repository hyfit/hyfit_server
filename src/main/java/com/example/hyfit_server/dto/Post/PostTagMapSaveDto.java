package com.example.hyfit_server.dto.Post;

import com.example.hyfit_server.domain.post.PostTagMapEntity;
import com.example.hyfit_server.domain.post.PostTagMapRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostTagMapSaveDto {

    private long postId;

    private long tagId;

    public PostTagMapEntity toEntity() {
        PostTagMapEntity postTagMapEntity = PostTagMapEntity.builder()
                .postId(postId)
                .tagId(tagId)
                .build();
        return postTagMapEntity;
    }

}
