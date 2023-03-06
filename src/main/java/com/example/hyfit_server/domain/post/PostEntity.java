package com.example.hyfit_server.domain.post;

import com.example.hyfit_server.domain.BaseTimeEntity;
import com.example.hyfit_server.dto.Post.PostDto;
import com.example.hyfit_server.dto.Post.PostModifyDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Table(name = "post")
@Getter
@NoArgsConstructor
@Entity
public class PostEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    private String email;

    @Column(nullable = true)
    private long boardId;

    @Column(nullable = true)
    private long locationId;

    @Size(max = 100)
    @Column(nullable = false)
    private String title;

    private String content;


    @Builder
    public PostEntity(long postId, String email, long boardId, long locationId, String title, String content){
        this.postId = postId;
        this.email = email;
        this.boardId = boardId;
        this.locationId = locationId;
        this.title = title;
        this.content = content;
    }

    public PostDto toDto(){
        return PostDto.builder()
                .postId(postId)
                .email(email)
                .boardId(boardId)
                .location_id(locationId)
                .title(title)
                .content(content)
                .build();
    }

    public PostEntity modify(PostModifyDto postModifyDto) {
        this.title = postModifyDto.getTitle();
        this.content = postModifyDto.getContent();
        return this;
    }
}
