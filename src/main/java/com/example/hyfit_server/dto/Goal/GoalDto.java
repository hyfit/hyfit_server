package com.example.hyfit_server.dto.Goal;

import com.example.hyfit_server.domain.user.GoalEntity;
import com.example.hyfit_server.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GoalDto {

    private long goalId;

    private String email;

    private String mountain;

    private int goalStatus;

    private String rate;

    private String description;


    /* DTO -> Entity */
    public GoalEntity toEntity(){
        GoalEntity goalEntity = GoalEntity.builder().
                goalId(goalId)
                .email(email)
                .mountain(mountain)
                .goalStatus(goalStatus)
                .rate(rate)
                .description(description)
                .build();
        return goalEntity;
    }

    @Builder
    public GoalDto(long goalId, String email, String mountain, int goalStatus,
                   String rate, String description){
        this.goalId = goalId;
        this. email = email;
        this.mountain = mountain;
        this.goalStatus = goalStatus;
        this.rate = rate;
        this.description = description;
    }



}
