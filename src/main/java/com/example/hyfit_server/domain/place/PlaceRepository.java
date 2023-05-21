package com.example.hyfit_server.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PlaceRepository extends JpaRepository<PlaceEntity,Long>, JpaSpecificationExecutor<PlaceEntity> {

    List<PlaceEntity> findAllByTypeAndContinents(String type, String continents);

    PlaceEntity findByName(String name);

    List<PlaceEntity> findAll();
}
