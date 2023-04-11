package com.example.hyfit_server.dto.location;

import com.example.hyfit_server.domain.location.LocationEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@Data
public class LocationDto {

    private long locationId;

    private String latitude;

    private String longitude;

    private String altitude;

    private long exerciseId;

    private long postId;

    @Builder
    public LocationDto(long locationId, String latitude, String longitude, String altitude, long exerciseId, long postId){
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.exerciseId = exerciseId;
        this.postId= postId;
    }

    public LocationEntity toEntity(){
        return LocationEntity.builder()
                .locationId(locationId)
                .latitude(latitude)
                .longitude(longitude)
                .altitude(altitude)
                .exerciseId(exerciseId)
                .postId(postId)
                .build();
    }
}
