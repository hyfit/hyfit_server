package com.example.hyfit_server.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -1634488958L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final com.example.hyfit_server.domain.QBaseTimeEntity _super = new com.example.hyfit_server.domain.QBaseTimeEntity(this);

    public final StringPath birth = createString("birth");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final StringPath grade = createString("grade");

    public final StringPath introduce = createString("introduce");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath profile_img = createString("profile_img");

    public final EnumPath<UserRole> role = createEnum("role", UserRole.class);

    public final NumberPath<Integer> user_status = createNumber("user_status", Integer.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

