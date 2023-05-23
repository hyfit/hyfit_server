package com.example.hyfit_server.dto.Post;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DeleteCommentReq {

    private String email;
    private long postId;
    private long commentId;

    @Builder
    public DeleteCommentReq( String email, long postId, long commentId){
        this.email = email;
        this.postId = postId;
        this.commentId = commentId;
    }
}
