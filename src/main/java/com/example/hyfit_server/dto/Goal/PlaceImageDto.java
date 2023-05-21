package com.example.hyfit_server.dto.Goal;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PlaceImageDto {

    private long placeId;

    private String type;

    private String name;

    private String continents;

    private String altitude;

    private String location;

    private String src;

    @Builder
    public PlaceImageDto(long placeId, String type, String name, String continents,
                         String altitude, String location,String src){
        this.placeId = placeId;
        this.name = name;
        this.type = type;
        this.continents = continents;
        this.altitude = altitude;
        this.location = location;
        this.src = src;
    }
}
