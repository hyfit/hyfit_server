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

    private long boardId;

    private long locationId;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    private String content;

    // location, board 구현 전이라 해당 값 임의로 설정
    public PostEntity toEntity() {
        PostEntity postEntity = PostEntity.builder()
                .email(email)
                .boardId(boardId)
                .locationId(locationId)
                .title(title)
                .content(content)
                .build();
        return postEntity;
    }

//    @Builder
//    public PostSaveDto(String email, long boardId, long locationId, String title, String content){
//        this.email = email;
//        this.boardId = boardId;
//        this.locationId = locationId;
//        this.title = title;
//        this.content = content;
//    }

}
