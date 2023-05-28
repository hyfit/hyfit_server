package com.example.hyfit_server.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFollowEntity is a Querydsl query type for FollowEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFollowEntity extends EntityPathBase<FollowEntity> {

    private static final long serialVersionUID = 1290161128L;

    public static final QFollowEntity followEntity = new QFollowEntity("followEntity");

    public final StringPath followerEmail = createString("followerEmail");

    public final NumberPath<Long> followId = createNumber("followId", Long.class);

    public final StringPath followingEmail = createString("followingEmail");

    public QFollowEntity(String variable) {
        super(FollowEntity.class, forVariable(variable));
    }

    public QFollowEntity(Path<? extends FollowEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFollowEntity(PathMetadata metadata) {
        super(FollowEntity.class, metadata);
    }

}

