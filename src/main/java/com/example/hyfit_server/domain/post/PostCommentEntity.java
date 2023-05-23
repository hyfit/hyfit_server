package com.example.hyfit_server.domain.post;

import com.example.hyfit_server.domain.BaseTimeEntity;
import com.example.hyfit_server.dto.Post.PostCommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "postComment")
@Getter
@NoArgsConstructor
@Entity
public class PostCommentEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    private long postId;
    private String content;
    private String email;


    @Builder
    public PostCommentEntity(long commentId, long postId, String content, String email) {
        this.commentId = commentId;
        this.postId = postId;
        this.content = content;
        this.email = email;
    }

    public PostCommentDto toDto() {
        PostCommentDto postCommentDto = PostCommentDto.builder()
                .commentId(commentId)
                .postId(postId)
                .content(content)
                .email(email)
                .build();
        return postCommentDto;
    }
}
