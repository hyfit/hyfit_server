package com.example.hyfit_server.dto.exercise;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ExerciseEndReq {

    private long exerciseId;

    private long totalTime;

    private String pace;

    private String distance;

    private String increase;

    private String end;
}
