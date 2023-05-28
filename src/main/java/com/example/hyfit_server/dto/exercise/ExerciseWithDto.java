package com.example.hyfit_server.dto.exercise;

import com.example.hyfit_server.domain.exercise.ExerciseEntity;
import com.example.hyfit_server.domain.exercise.ExerciseWithEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExerciseWithDto {
    private int exerciseWithId;

    private String user1Email;

    private String user2Email;

    private int user1ExerciseId;

    private int user2ExerciseId;

    // dto -> entity
    public ExerciseWithEntity toEntity(){
        ExerciseWithEntity exerciseWithEntity = ExerciseWithEntity.builder().
                exerciseWithId(exerciseWithId).
                user1Email(user1Email).
                user2Email(user2Email).
                user1ExerciseId(user1ExerciseId).
                user2ExerciseId(user2ExerciseId)
                .build();
        return exerciseWithEntity;

    }

    @Builder
    public ExerciseWithDto(int exerciseWithId,String user1Email, String user2Email, int user1ExerciseId, int user2ExerciseId){
        this.exerciseWithId = exerciseWithId;
        this.user1Email = user1Email;
        this.user2Email = user2Email;
        this.user1ExerciseId = user1ExerciseId;
        this.user2ExerciseId = user2ExerciseId;
    }
}
