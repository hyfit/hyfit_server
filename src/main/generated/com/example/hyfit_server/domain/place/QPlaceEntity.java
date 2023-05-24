package com.example.hyfit_server.domain.place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlaceEntity is a Querydsl query type for PlaceEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceEntity extends EntityPathBase<PlaceEntity> {

    private static final long serialVersionUID = 1934205140L;

    public static final QPlaceEntity placeEntity = new QPlaceEntity("placeEntity");

    public final StringPath altitude = createString("altitude");

    public final StringPath continents = createString("continents");

    public final StringPath location = createString("location");

    public final StringPath name = createString("name");

    public final NumberPath<Long> placeId = createNumber("placeId", Long.class);

    public final StringPath type = createString("type");

    public QPlaceEntity(String variable) {
        super(PlaceEntity.class, forVariable(variable));
    }

    public QPlaceEntity(Path<? extends PlaceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlaceEntity(PathMetadata metadata) {
        super(PlaceEntity.class, metadata);
    }

}

