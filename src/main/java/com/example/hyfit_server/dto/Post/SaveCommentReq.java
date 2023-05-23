package com.example.hyfit_server.dto.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class SaveCommentReq {
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String content;
}
