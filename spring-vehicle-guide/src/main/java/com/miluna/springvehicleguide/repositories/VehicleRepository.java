package com.miluna.springvehicleguide.repositories;

import com.miluna.springvehicleguide.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository(value = "VehicleRepository")
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {


    // BASIC SEARCH
    List<VehicleEntity> findByBasePriceBetween(Number priceA, Number priceB);

    List<VehicleEntity> findByYear(Date year);

    List<VehicleEntity> findByName(String modelname);


    // SEARCH BY BRAND
    @Query( value = "SELECT A " +
            "FROM vehicles A, brands B " +
            "WHERE B.name=?0", nativeQuery = true)
    List<VehicleEntity> findByBrandName(String name);


    // SEARCH BY ENGINE
    @Query( value = "SELECT A " +
            "FROM vehicles A " +
            "INNER JOIN engines B " +
            "ON A.engineId=B.id " +
            "WHERE B.displacement >= ?0", nativeQuery = true)
    List<VehicleEntity> findByDisplacementBiggerThan(Integer displacement);

    @Query( value = "SELECT A " +
            "FROM vehicles A " +
            "INNER JOIN engines B " +
            "ON A.engineId=B.id " +
            "WHERE B.displacement <= ?0", nativeQuery = true)
    List<VehicleEntity> findByDisplacementSmallerThan(Integer displacement);


    @Query( value = "SELECT A " +
            "FROM vehicles A " +
            "INNER JOIN engines B " +
            "ON A.engineId=B.id " +
            "WHERE B.displacement BETWEEN ?0 AND ?1", nativeQuery = true)
    List<VehicleEntity> findByDisplacementBetween(Integer displacementA, Integer displacementB);

}
