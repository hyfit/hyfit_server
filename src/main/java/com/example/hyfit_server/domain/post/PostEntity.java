package com.example.hyfit_server.domain.post;

import com.example.hyfit_server.domain.BaseTimeEntity;
import com.example.hyfit_server.dto.Post.PostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "post")
@Getter
@NoArgsConstructor
@Entity
public class PostEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    private String email;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String content;


    @Builder
    public PostEntity(long postId, String email, String type, String content){
        this.postId = postId;
        this.email = email;
        this.type = type;
        this.content = content;
    }

    public PostDto toDto(){
        return PostDto.builder()
                .postId(postId)
                .email(email)
                .type(type)
                .content(content)
                .build();
    }

    public PostEntity modifyContent(String content) {
        this.content = content;
        return this;
    }
}
