package com.miluna.springvehicleguide.repositories;

import com.miluna.springvehicleguide.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository(value = "BrandRepository")
public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findAllByCountry(String country);

    List<Brand> findAllByYear(Date year);

}
