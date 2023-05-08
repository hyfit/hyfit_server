package com.example.hyfit_server.domain.post;

import com.example.hyfit_server.dto.Post.PostLikeDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

import javax.persistence.*;

@Table(name = "postLike")
@Getter
@NoArgsConstructor
@Entity
public class PostLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postLikeId;

    private long postId;

    private String email;

    @Builder
    public PostLikeEntity(long postLikeId, long postId, String email) {
        this.postLikeId = postLikeId;
        this.postId = postId;
        this.email = email;
    }

    public PostLikeDto toDto() {
        PostLikeDto postLikeDto = PostLikeDto.builder()
                .postLikeId(postLikeId)
                .postId(postId)
                .email(email)
                .build();
        return postLikeDto;
    }


}
