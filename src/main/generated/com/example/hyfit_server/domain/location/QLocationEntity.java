package com.example.hyfit_server.domain.location;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocationEntity is a Querydsl query type for LocationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocationEntity extends EntityPathBase<LocationEntity> {

    private static final long serialVersionUID = 145480258L;

    public static final QLocationEntity locationEntity = new QLocationEntity("locationEntity");

    public final com.example.hyfit_server.domain.QBaseTimeEntity _super = new com.example.hyfit_server.domain.QBaseTimeEntity(this);

    public final StringPath altitude = createString("altitude");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> exerciseId = createNumber("exerciseId", Long.class);

    public final StringPath latitude = createString("latitude");

    public final NumberPath<Long> locationId = createNumber("locationId", Long.class);

    public final StringPath longitude = createString("longitude");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public QLocationEntity(String variable) {
        super(LocationEntity.class, forVariable(variable));
    }

    public QLocationEntity(Path<? extends LocationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocationEntity(PathMetadata metadata) {
        super(LocationEntity.class, metadata);
    }

}

