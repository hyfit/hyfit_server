package com.example.hyfit_server.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostCommentEntity is a Querydsl query type for PostCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostCommentEntity extends EntityPathBase<PostCommentEntity> {

    private static final long serialVersionUID = -1874868349L;

    public static final QPostCommentEntity postCommentEntity = new QPostCommentEntity("postCommentEntity");

    public final com.example.hyfit_server.domain.QBaseTimeEntity _super = new com.example.hyfit_server.domain.QBaseTimeEntity(this);

    public final NumberPath<Long> commentId = createNumber("commentId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public QPostCommentEntity(String variable) {
        super(PostCommentEntity.class, forVariable(variable));
    }

    public QPostCommentEntity(Path<? extends PostCommentEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostCommentEntity(PathMetadata metadata) {
        super(PostCommentEntity.class, metadata);
    }

}

