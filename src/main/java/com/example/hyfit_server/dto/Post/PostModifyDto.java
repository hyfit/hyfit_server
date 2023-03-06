package com.example.hyfit_server.dto.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostModifyDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    private String content;


}
