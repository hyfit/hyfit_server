package com.example.hyfit_server.dto.Goal;

import com.example.hyfit_server.domain.user.GoalEntity;
import com.example.hyfit_server.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GoalAddDto {

    private String email;

    private String mountain;

    private int goalStatus;

    private String rate;

    private String description;

    /* DTO -> Entity */
    public GoalEntity toEntity(){
        GoalEntity goalEntity = GoalEntity.builder()
                .email(email)
                .mountain(mountain)
                .goalStatus(1)
                .rate("0")
                .description(description)
                .build();
        return goalEntity;
    }

//    @Builder
//    public GoalAddDto(String email, String mountain, String description){
//        this.email = email;
//        this.mountain = mountain;
//        this.description = description;
//        this.goalStatus = 1; // add 시에는 status 1
//        this.rate = "0"; // add 시에는 status 0%
//    }


}