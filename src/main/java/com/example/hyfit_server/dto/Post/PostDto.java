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

    private long exercise_data_id;

    private String title;

    private String content;


    public PostEntity toEntity() {
        PostEntity postEntity = PostEntity.builder()
                .postId(postId)
                .email(email)
                .exercise_data_id(exercise_data_id)
                .title(title)
                .content(content)
                .build();
        return postEntity;
    }

    @Builder
    public PostDto(long postId, String email, long exercise_data_id, String title, String content){
        this.postId = postId;
        this.email = email;
        this.exercise_data_id = exercise_data_id;
        this.title = title;
        this.content = content;
    }
}
