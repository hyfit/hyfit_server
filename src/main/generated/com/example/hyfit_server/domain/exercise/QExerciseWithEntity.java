package com.example.hyfit_server.domain.exercise;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExerciseWithEntity is a Querydsl query type for ExerciseWithEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseWithEntity extends EntityPathBase<ExerciseWithEntity> {

    private static final long serialVersionUID = -114752888L;

    public static final QExerciseWithEntity exerciseWithEntity = new QExerciseWithEntity("exerciseWithEntity");

    public final com.example.hyfit_server.domain.QBaseTimeEntity _super = new com.example.hyfit_server.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> exerciseWithId = createNumber("exerciseWithId", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath user1Email = createString("user1Email");

    public final NumberPath<Integer> user1ExerciseId = createNumber("user1ExerciseId", Integer.class);

    public final StringPath user2Email = createString("user2Email");

    public final NumberPath<Integer> user2ExerciseId = createNumber("user2ExerciseId", Integer.class);

    public QExerciseWithEntity(String variable) {
        super(ExerciseWithEntity.class, forVariable(variable));
    }

    public QExerciseWithEntity(Path<? extends ExerciseWithEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExerciseWithEntity(PathMetadata metadata) {
        super(ExerciseWithEntity.class, metadata);
    }

}

