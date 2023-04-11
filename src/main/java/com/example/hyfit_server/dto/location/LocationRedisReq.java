package com.example.hyfit_server.dto.location;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LocationRedisReq {
    private String latitude;

    private String longitude;

    private String altitude;

    private long id;
}
