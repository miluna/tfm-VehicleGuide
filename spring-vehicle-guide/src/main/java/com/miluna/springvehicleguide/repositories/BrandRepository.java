package com.miluna.springvehicleguide.repositories;

import com.miluna.springvehicleguide.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository(value = "BrandRepository")
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    List<BrandEntity> findAllByCountry(String country);

    List<BrandEntity> findAllByYear(Date year);

}
