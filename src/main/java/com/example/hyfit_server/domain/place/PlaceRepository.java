package com.example.hyfit_server.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<PlaceEntity,Long> {

    List<PlaceEntity> findAllByTypeAndContinents(String type, String continents);

    PlaceEntity findByName(String name);
}
