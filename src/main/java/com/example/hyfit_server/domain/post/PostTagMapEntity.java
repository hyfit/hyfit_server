package com.example.hyfit_server.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "post_tag_map")
@Getter
@NoArgsConstructor
@Entity
public class PostTagMapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postTagMapId;

    private long postId;

    private long tagId;

    @Builder
    public PostTagMapEntity(long postTagMapId, long postId, long tagId){
        this.postTagMapId = postTagMapId;
        this.postId = postId;
        this.tagId = tagId;
    }
}
