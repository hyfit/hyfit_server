package com.example.hyfit_server.domain.exercise;

import com.example.hyfit_server.domain.BaseTimeEntity;
import com.example.hyfit_server.dto.exercise.ExerciseDto;
import com.example.hyfit_server.dto.exercise.ExerciseEndReq;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Table(name = "exercise")
@Getter
@NoArgsConstructor
@Entity
public class ExerciseEntity extends BaseTimeEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exerciseId;

    @Column(nullable = true)
    private long goalId;

    private String email;

    private String type;

    @Column(nullable = true)
    private String pace;

    private LocalDateTime start;
    @Column(nullable = true)
    private LocalDateTime end;
    @Column(nullable = true)
    private long totalTime;
    @Column(nullable = true)
    private String distance;

    @Builder
    public ExerciseEntity(long exerciseId, long goalId, String email, String type, String pace,String distance, LocalDateTime start, LocalDateTime end, long totalTime){
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

    public ExerciseDto toDto(){
        return ExerciseDto.builder()
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

    }

    public ExerciseEntity exerciseEnd(ExerciseEndReq exerciseEndReq){

        this.totalTime = exerciseEndReq.getTotalTime();
        this.pace = exerciseEndReq.getPace();
        this.end = exerciseEndReq.getEnd();
        this.distance = exerciseEndReq.getDistance();
        return this;
    }

}
