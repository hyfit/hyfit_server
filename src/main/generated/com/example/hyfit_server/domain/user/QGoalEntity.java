package com.example.hyfit_server.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoalEntity is a Querydsl query type for GoalEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoalEntity extends EntityPathBase<GoalEntity> {

    private static final long serialVersionUID = 345221834L;

    public static final QGoalEntity goalEntity = new QGoalEntity("goalEntity");

    public final com.example.hyfit_server.domain.QBaseTimeEntity _super = new com.example.hyfit_server.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final StringPath email = createString("email");

    public final StringPath gain = createString("gain");

    public final NumberPath<Long> goalId = createNumber("goalId", Long.class);

    public final NumberPath<Integer> goalStatus = createNumber("goalStatus", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath place = createString("place");

    public final StringPath rate = createString("rate");

    public final StringPath type = createString("type");

    public QGoalEntity(String variable) {
        super(GoalEntity.class, forVariable(variable));
    }

    public QGoalEntity(Path<? extends GoalEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoalEntity(PathMetadata metadata) {
        super(GoalEntity.class, metadata);
    }

}

