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

    private long boardId;

    private long locationId;

    private String title;

    private String content;


    public PostEntity toEntity() {
        PostEntity postEntity = PostEntity.builder()
                .postId(postId)
                .email(email)
                .boardId(boardId)
                .locationId(locationId)
                .title(title)
                .content(content)
                .build();
        return postEntity;
    }

    @Builder
    public PostDto(long postId, String email, long boardId, long location_id, String title, String content){
        this.postId = postId;
        this.email = email;
        this.boardId = boardId;
        this.locationId = location_id;
        this.title = title;
        this.content = content;
    }
}
