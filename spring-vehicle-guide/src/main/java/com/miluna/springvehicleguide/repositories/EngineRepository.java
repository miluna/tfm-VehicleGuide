package com.miluna.springvehicleguide.repositories;

import com.miluna.springvehicleguide.models.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "EngineRepository")
public interface EngineRepository extends JpaRepository<Engine, Long> {
}
