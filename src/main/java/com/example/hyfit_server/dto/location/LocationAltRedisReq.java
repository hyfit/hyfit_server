package com.example.hyfit_server.dto.location;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LocationAltRedisReq {
    private String latitude;

    private String longitude;

    private String altitude;

    private String increase;

    private long id;
}
