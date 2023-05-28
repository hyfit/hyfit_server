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

    private String start;

    private String end;

    private long totalTime;

    private String distance;

    private String increase;

    private String peakAlt;

    /* DTO -> Entity */
    public ExerciseEntity toEntity(){
        ExerciseEntity exerciseEntity = ExerciseEntity.builder()
                .exerciseId(exerciseId)
                .goalId(goalId)
                .email(email)
                .type(type)
                .pace(pace)
                .start(LocalDateTime.parse(start))
                .end(LocalDateTime.parse(end))
                .totalTime(totalTime)
                .distance(distance)
                .increase(increase)
                .peakAlt(peakAlt)
                .build();
        return exerciseEntity;
    }

    @Builder
    public ExerciseDto(long exerciseId, long goalId, String email, String type, String pace,String distance, String start, String end, long totalTime,String increase,String peakAlt){
        this.exerciseId = exerciseId;
        this.goalId = goalId;
        this.email = email;
        this.type = type;
        this.pace = pace;
        this.distance=distance;
        this.start = start;
        this.end = end;
        this.totalTime = totalTime;
        this.increase = increase;
        this.peakAlt = peakAlt;
    }

}
