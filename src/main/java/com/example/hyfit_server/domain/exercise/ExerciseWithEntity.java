package com.example.hyfit_server.domain.exercise;

import com.example.hyfit_server.domain.BaseTimeEntity;
import com.example.hyfit_server.dto.exercise.ExerciseWithDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "exercise_with")
@Getter
@NoArgsConstructor
@Entity
public class ExerciseWithEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseWithId;

    private String user1Email;

    private String user2Email;

    private int user1ExerciseId;

    private int user2ExerciseId;

    @Builder
    public ExerciseWithEntity(int exerciseWithId,String user1Email, String user2Email, int user1ExerciseId, int user2ExerciseId){
        this.exerciseWithId = exerciseWithId;
        this.user1Email = user1Email;
        this.user2Email = user2Email;
        this.user1ExerciseId = user1ExerciseId;
        this.user2ExerciseId = user2ExerciseId;
    }


    public ExerciseWithDto toDto(){
        ExerciseWithDto exerciseWithDto = ExerciseWithDto.builder().
        exerciseWithId(exerciseWithId).
                user1Email(user1Email).
                user2Email(user2Email).
                user1ExerciseId(user1ExerciseId).
                user2ExerciseId(user2ExerciseId)
                .build();
        return exerciseWithDto;
    }


    public ExerciseWithEntity startExerciseWith1(int user1ExerciseId){
        this.user1ExerciseId = user1ExerciseId;
        return this;
    }

    public ExerciseWithEntity startExerciseWith2(int user2ExerciseId) {
        this.user2ExerciseId = user2ExerciseId;
        return this;
    }
}
