package com.example.hyfit_server.domain.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseWithRepository  extends JpaRepository<ExerciseWithEntity,Integer> {
    ExerciseWithEntity findByExerciseWithId(int exerciseWithId);
}
