package com.example.hyfit_server.dto.Post;

import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import javax.persistence.Column;

@NoArgsConstructor
@Data
public class PostPageReq {
    private Long lastPostId;
    private String searchType;
    private int size;


}
