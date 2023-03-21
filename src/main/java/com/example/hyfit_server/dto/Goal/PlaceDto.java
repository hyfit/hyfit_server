package com.example.hyfit_server.dto.Goal;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PlaceDto {

    private long placeId;

    private String type;

    private String name;

    private String continents;

    private String altitude;

    @Builder
    public PlaceDto(long placeId, String type, String name, String continents,
                   String altitude){
        this.placeId = placeId;
        this.type = type;
        this.name = name;
        this.type = type;
        this.continents = continents;
        this.altitude = altitude;
    }
}
