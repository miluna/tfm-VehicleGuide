package com.miluna.springvehicleguide.repositories;

import com.miluna.springvehicleguide.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository(value = "VehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


    // BASIC SEARCH
    List<Vehicle> findByBasePriceBetween(Number priceA, Number priceB);

    List<Vehicle> findByYear(Date year);

    List<Vehicle> findByName(String modelname);


    // SEARCH BY BRAND
    @Query( "SELECT Vehicle " +
            "FROM Vehicle, Brand " +
            "WHERE Brand.name=?0")
    List<Vehicle> findByBrandName(String name);


    // SEARCH BY ENGINE
    @Query( "SELECT Vehicle " +
            "FROM Vehicle, Engine " +
            "WHERE Vehicle.EngineId=Engine.id " +
            "AND Engine.displacement >= ?0")
    List<Vehicle> findByDisplacementBiggerThan(Number displacement);

    @Query( "SELECT Vehicle " +
            "FROM Vehicle, Engine " +
            "WHERE Vehicle.EngineId=Engine.id " +
            "AND Engine.displacement <= ?0")
    List<Vehicle> findByDisplacementSmallerThan(Number displacement);


    @Query( "SELECT Vehicle " +
            "FROM Vehicle, Engine " +
            "WHERE Vehicle.EngineId=Engine.id " +
            "AND Engine.displacement BETWEEN ?0 AND ?1")
    List<Vehicle> findByDisplacementBetween(Number displacementA, Number displacementB);

}
