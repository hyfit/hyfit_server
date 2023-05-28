package com.example.hyfit_server.domain.exercise;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExerciseEntity is a Querydsl query type for ExerciseEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseEntity extends EntityPathBase<ExerciseEntity> {

    private static final long serialVersionUID = -1781167710L;

    public static final QExerciseEntity exerciseEntity = new QExerciseEntity("exerciseEntity");

    public final com.example.hyfit_server.domain.QBaseTimeEntity _super = new com.example.hyfit_server.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath distance = createString("distance");

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> end = createDateTime("end", java.time.LocalDateTime.class);

    public final NumberPath<Long> exerciseId = createNumber("exerciseId", Long.class);

    public final NumberPath<Long> goalId = createNumber("goalId", Long.class);

    public final StringPath increase = createString("increase");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath pace = createString("pace");

    public final StringPath peakAlt = createString("peakAlt");

    public final DateTimePath<java.time.LocalDateTime> start = createDateTime("start", java.time.LocalDateTime.class);

    public final NumberPath<Long> totalTime = createNumber("totalTime", Long.class);

    public final StringPath type = createString("type");

    public QExerciseEntity(String variable) {
        super(ExerciseEntity.class, forVariable(variable));
    }

    public QExerciseEntity(Path<? extends ExerciseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExerciseEntity(PathMetadata metadata) {
        super(ExerciseEntity.class, metadata);
    }

}

