package com.example.hyfit_server.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<GoalEntity,Long> {

    List<GoalEntity> findAllByEmailAndGoalStatus(String email, int status);

    GoalEntity findByPlaceAndEmailAndGoalStatus(String place, String email, int status);

    GoalEntity findByGoalId(long id);

    List<GoalEntity> findAllByEmail(String email);


}
