package com.example.hyfit_server.domain.location;

import com.example.hyfit_server.domain.BaseTimeEntity;
import com.example.hyfit_server.dto.location.LocationDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "location")
@Getter
@NoArgsConstructor
@Entity
public class LocationEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long locationId;

    private String latitude;

    private String longitude;

    private String altitude;

    @Column(nullable = true)
    private long exerciseId;

    @Column(nullable = true)
    private long postId;

    @Builder
    public LocationEntity(long locationId, String latitude, String longitude, String altitude, long exerciseId, long postId){
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.exerciseId = exerciseId;
        this.postId= postId;
    }

    public LocationDto toDto(){
        return LocationDto.builder()
                .locationId(locationId)
                .latitude(latitude)
                .longitude(longitude)
                .altitude(altitude)
                .exerciseId(exerciseId)
                .postId(postId)
                .build();
    }
}
