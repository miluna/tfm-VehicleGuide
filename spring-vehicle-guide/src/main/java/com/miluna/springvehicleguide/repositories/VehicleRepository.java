package com.miluna.springvehicleguide.repositories;

import com.miluna.springvehicleguide.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository(value = "VehicleRepository")
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {


    // BASIC SEARCH
    List<VehicleEntity> findByBasePriceBetween(Number priceA, Number priceB);

    List<VehicleEntity> findByYear(Date year);

    List<VehicleEntity> findByName(String modelname);


    // SEARCH BY BRAND
    @Query( "SELECT Vehicle " +
            "FROM Vehicle, Brand " +
            "WHERE Brand.name=?0")
    List<VehicleEntity> findByBrandName(String name);


    // SEARCH BY ENGINE
    @Query( "SELECT Vehicle " +
            "FROM Vehicle, Engine " +
            "WHERE Vehicle.EngineId=Engine.id " +
            "AND Engine.displacement >= ?0")
    List<VehicleEntity> findByDisplacementBiggerThan(Number displacement);

    @Query( "SELECT Vehicle " +
            "FROM Vehicle, Engine " +
            "WHERE Vehicle.EngineId=Engine.id " +
            "AND Engine.displacement <= ?0")
    List<VehicleEntity> findByDisplacementSmallerThan(Number displacement);


    @Query( "SELECT Vehicle " +
            "FROM Vehicle, Engine " +
            "WHERE Vehicle.EngineId=Engine.id " +
            "AND Engine.displacement BETWEEN ?0 AND ?1")
    List<VehicleEntity> findByDisplacementBetween(Number displacementA, Number displacementB);

}
