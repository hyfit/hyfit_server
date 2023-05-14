package com.example.hyfit_server.dto.location;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LocationRedisRes {
    private String start;
    private String middle;
    private String end;

    @Builder
    public LocationRedisRes(String start, String middle, String end){
        this.start = start;
        this.middle=middle;
        this.end=end;
    }
}
