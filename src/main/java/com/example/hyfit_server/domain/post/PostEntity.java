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


    private long exercise_data_id;

    @Size(max = 100)
    @Column(nullable = false)
    private String title;

    private String content;


    @Builder
    public PostEntity(long postId, String email, long exercise_data_id,String title, String content){
        this.postId = postId;
        this.email = email;
        this.exercise_data_id = exercise_data_id;
        this.title = title;
        this.content = content;
    }

    public PostDto toDto(){
        return PostDto.builder()
                .postId(postId)
                .email(email)
                .exercise_data_id(exercise_data_id)
                .title(title)
                .content(content)
                .build();
    }

    public void modifyTitle(String title) {
        this.title = title;
    }

    public void modifyContent(String content) {
        this.content = content;
    }
}
