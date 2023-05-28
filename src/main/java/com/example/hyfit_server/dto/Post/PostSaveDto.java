package com.example.hyfit_server.dto.Post;

import com.example.hyfit_server.domain.post.PostEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class PostSaveDto {

    private String email;

    private String type;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    public PostEntity toEntity() {
        PostEntity postEntity = PostEntity.builder()
                .email(email)
                .type(type)
                .content(content)
                .build();
        return postEntity;
    }

//    @Builder
//    public PostSaveDto(String email, long exercise_data_id, String title, String content){
//        this.email = email;
//        this.exercise_data_id = exercise_data_id;
//        this.title = title;
//        this.content = content;
//    }

}
