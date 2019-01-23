package com.miluna.springvehicleguide.repositories;

import com.miluna.springvehicleguide.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository(value = "VehicleRepository")
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    // SEARCH BY BRAND
    @Query( value = "SELECT A.* " +
            "FROM vehicles A " +
            "INNER JOIN brands B " +
            "ON A.brand_id=B.id " +
            "WHERE B.id=:id", nativeQuery = true)
    List<VehicleEntity> findByBrand(@Param("id") Long id);

}
