package com.example.hyfit_server.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<GoalEntity,Long> {
    GoalEntity findByMountainAndEmailAndGoalStatus(String mountain,String email,int status);
    List<GoalEntity> findAllByEmailAndGoalStatus(String email, int status);
}
