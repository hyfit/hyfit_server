package com.example.hyfit_server.dto.Goal;

import com.example.hyfit_server.domain.user.GoalEntity;
import com.example.hyfit_server.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class GoalDto {

    private long goalId;

    private String email;

    private String place;

    private String type;

    private int goalStatus;

    private String rate;

    private String description;

    private String createdAt;



    /* DTO -> Entity */
    public GoalEntity toEntity(){
        GoalEntity goalEntity = GoalEntity.builder().
                goalId(goalId)
                .email(email)
                .place(place)
                .type(type)
                .goalStatus(goalStatus)
                .rate(rate)
                .description(description)
                .build();
        return goalEntity;
    }

    @Builder
    public GoalDto(long goalId, String email, String place, String type, int goalStatus,
                   String rate, String description,String createdAt){
        this.goalId = goalId;
        this. email = email;
        this.place = place;
        this.type = type;
        this.goalStatus = goalStatus;
        this.rate = rate;
        this.description = description;
        this.createdAt = createdAt;
    }



}
