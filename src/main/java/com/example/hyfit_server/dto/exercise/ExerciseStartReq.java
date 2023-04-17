package com.example.hyfit_server.dto.exercise;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@Data
public class ExerciseStartReq {

    private String type;

    private LocalDateTime start;

    private long goalId;
}
