package com.example.hyfit_server.domain.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageEntity is a Querydsl query type for ImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImageEntity extends EntityPathBase<ImageEntity> {

    private static final long serialVersionUID = 691721340L;

    public static final QImageEntity imageEntity = new QImageEntity("imageEntity");

    public final NumberPath<Long> imageId = createNumber("imageId", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Long> placeId = createNumber("placeId", Long.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public QImageEntity(String variable) {
        super(ImageEntity.class, forVariable(variable));
    }

    public QImageEntity(Path<? extends ImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageEntity(PathMetadata metadata) {
        super(ImageEntity.class, metadata);
    }

}

