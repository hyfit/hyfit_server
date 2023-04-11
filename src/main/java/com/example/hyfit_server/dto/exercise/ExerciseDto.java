package com.example.hyfit_server.dto.exercise;

import com.example.hyfit_server.domain.exercise.ExerciseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ExerciseDto {

    private long exerciseId;

    private long goalId;

    private String email;

    private String type;

    private String pace;

    private LocalDateTime start;

    private LocalDateTime end;

    private long totalTime;

    private String distance;

    /* DTO -> Entity */
    public ExerciseEntity toEntity(){
        ExerciseEntity exerciseEntity = ExerciseEntity.builder()
                .exerciseId(exerciseId)
                .goalId(goalId)
                .email(email)
                .type(type)
                .pace(pace)
                .start(start)
                .end(end)
                .totalTime(totalTime)
                .distance(distance)
                .build();
        return exerciseEntity;
    }

    @Builder
    public ExerciseDto(long exerciseId, long goalId, String email, String type, String pace,String distance, LocalDateTime start, LocalDateTime end, long totalTime){
        this.exerciseId = exerciseId;
        this.goalId = goalId;
        this.email = email;
        this.type = type;
        this.pace = pace;
        this.distance=distance;
        this.start = start;
        this.end = end;
        this.totalTime = totalTime;
    }

}
