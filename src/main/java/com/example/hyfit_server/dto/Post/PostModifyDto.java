package com.example.hyfit_server.dto.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostModifyDto {

    private Optional<String> content;
}
