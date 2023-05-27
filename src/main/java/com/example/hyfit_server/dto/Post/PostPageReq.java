package com.example.hyfit_server.dto.Post;

import lombok.Builder;
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

    @Builder
    public PostPageReq(Long lastPostId, String searchType, int size) {
        this.lastPostId = lastPostId;
        this.searchType = searchType;
        this.size = size;
    }


}
