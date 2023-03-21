package com.example.hyfit_server.domain.place;

import com.example.hyfit_server.dto.Goal.GoalDto;
import com.example.hyfit_server.dto.Goal.PlaceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "place")
@Getter
@NoArgsConstructor
@Entity
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long placeId;


    private String name;

    private String type;

    private String continents;

    private String altitude;


    public PlaceDto toDto(){
        return  PlaceDto.builder().
                placeId(placeId)
                .name(name)
                .type(type)
                .continents(continents)
                .altitude(altitude)
                .build();
    }
}

