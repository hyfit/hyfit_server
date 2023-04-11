package com.example.hyfit_server.dto.location;

import com.example.hyfit_server.domain.location.LocationEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LocationExerciseSaveReq {

    private String latitude;

    private String longitude;

    private String altitude;

    private long exerciseId;

    public LocationEntity toEntity(){
        return LocationEntity.builder()
                .latitude(latitude)
                .longitude(longitude)
                .altitude(altitude)
                .exerciseId(exerciseId)
                .build();
    }
}
