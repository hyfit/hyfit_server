package com.example.hyfit_server.domain.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity,Long> {

    ExerciseEntity findByExerciseId(long exerciseId);

}
